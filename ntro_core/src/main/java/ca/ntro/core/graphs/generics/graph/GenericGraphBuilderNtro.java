package ca.ntro.core.graphs.generics.graph;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.common.CycleException;
import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.common.EdgeTypeNtro;
import ca.ntro.core.graphs.common.NodeAlreadyAddedException;
import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.common.NodeIdNtro;
import ca.ntro.core.initialization.Factory;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.stream.Reducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericGraphBuilderNtro<N extends GenericNode<N,E,SO>,
                                              E extends GenericEdge<N,E,SO>,
                                              SO extends SearchOptions,
                                              NB extends GenericNodeBuilder<N,E,SO,NB>,
                                              G extends GenericGraph<N,E,SO>> 

       implements     GenericGraphBuilder<N,E,SO,NB,G> {
	
	private Class<N> nodeClass;
	private Class<E> edgeClass;

	private G graph;
	private Map<String, N> startNodes = new HashMap<>();
	
	protected abstract G newGraphInstance();
	protected abstract NB newNodeBuilderInstance();

	public Map<String, N> getStartNodes() {
		return startNodes;
	}

	public void setStartNodes(Map<String, N> nodes) {
		this.startNodes = nodes;
	}

	public G getGraph() {
		return graph;
	}
	public void setGraph(G graph) {
		this.graph = graph;
	}
	public Class<N> getNodeClass() {
		return nodeClass;
	}

	public void setNodeClass(Class<N> nodeClass) {
		this.nodeClass = nodeClass;
	}

	public Class<? extends E> getEdgeClass() {
		return edgeClass;
	}

	public void setEdgeClass(Class<E> edgeClass) {
		this.edgeClass = edgeClass;
	}

	public void initialize() {
		setGraph(newGraphInstance());
		graphNtro(getGraph()).setGraphStructure(this);
	}

	@Override
	public NB addNode(String nodeId) {
		NodeIdNtro nodeIdNtro = new NodeIdNtro(nodeId);

		return addNode(nodeIdNtro);
	}

	@Override
	public NB addNode(NodeId nodeId) {
		N node = Factory.newInstance(getNodeClass());
		
		nodeNtro(node).setGraph(graph());
		nodeNtro(node).setNodeId(nodeId);
		
		return addNode(node);
	}
	
	@SuppressWarnings("unchecked")
	private GenericNodeNtro<N,E,SO> nodeNtro(N node){
		return (GenericNodeNtro<N,E,SO>) node;
	}

	@SuppressWarnings("unchecked")
	private GenericNodeBuilderNtro<N,E,SO,NB> nodeBuilderNtro(NB nodeBuilder){
		return (GenericNodeBuilderNtro<N,E,SO,NB>) nodeBuilder;
	}

	@Override
	public NB addNode(N node) {
		NB builder = newNodeBuilderInstance();
		nodeBuilderNtro(builder).setGraphBuilder(this);
		nodeBuilderNtro(builder).setNode(node);
		
		nodeNtro(node).setNodeStructure(builder);
		
		memorizeNode(node);

		return builder;
	}

	@Override
	public NB findNode(String nodeId) {
		NodeIdNtro nodeIdNtro = new NodeIdNtro(nodeId);

		return findNode(nodeIdNtro);
	}

	@SuppressWarnings("unchecked")
	@Override
	public NB findNode(NodeId nodeId) {
		N node = graph().findNode(nodeId);

		return (NB) nodeNtro(node).nodeStructure();
	}

	@Override
	public E addEdge(NB fromNode, String edgeName, NB toNode) {
		EdgeType edgeTypeForward = new EdgeTypeNtro(Direction.FORWARD, edgeName);
		EdgeType edgeTypeBackward = new EdgeTypeNtro(Direction.BACKWARD, edgeName);

		E forwardEdge = addEdge(fromNode,edgeTypeForward,toNode);

		addEdge(toNode, edgeTypeBackward, fromNode);

		if(!toNode.node().isPartOfCycle()) {

		    nodeBuilderNtro(toNode).setIsStartNode(false);
		    removeStartNode(toNode.node());

		}else {
			
			onCycleDetected();

		}

		return forwardEdge;
	}

	protected void onCycleDetected() {
	}

	public void removeStartNode(N node) {
		getStartNodes().remove(node.id().toKey().toString());
	}

	protected E addEdge(NB fromNode, EdgeType edgeType, NB toNode) {

		E edge = createEdge(fromNode, edgeType, toNode);

		nodeBuilderNtro(fromNode).addEdge(edge);
		
		return edge;
	}

    @SuppressWarnings("unchecked")
	private GenericEdgeNtro<N,E,SO> edgeNtro(E edge){
		return (GenericEdgeNtro<N,E,SO>) edge;
    }

	protected E createEdge(NB fromNode, EdgeType edgeType, NB toNode) {
		
		E edge = Factory.newInstance(getEdgeClass());

		edgeNtro(edge).setFrom(fromNode.node());
		edgeNtro(edge).setTo(toNode.node());
		edgeNtro(edge).setEdgeType(edgeType);
		
		return edge;
	}

	protected void memorizeNode(N node) {
		if(ifNodeAlreadyExists(node)) {

			Ntro.exceptionThrower().throwException(new NodeAlreadyAddedException("Node already added: " + node.id().toKey()));

		}else {
			
			getStartNodes().put(node.id().toKey().toString(), node);

		}
	}

	protected boolean ifNodeAlreadyExists(N node) {
		return graph().reduceNodes(false, (accumulator, reachableNode) -> {
			
			if(accumulator == true) {
				throw new Break();
			}
			
			if(reachableNode.id().equals(node.id())) {
				accumulator = true;
			}
			
			return accumulator;

		}).value();
	}

	@Override
	public <R> void reduceStartNodes(ResultNtro<R> result, NodeReducer<N, E, SO, R> reducer) {
		if(result.hasException()) {
			return;
		}
		
		for(N node : startNodes.values()) {
			try {

				result.registerValue(reducer.reduceNode(result.value(), node));

			}catch(Throwable t) {
				
				result.registerException(t);
				return;
			}
		}
	}

	@Override
	public G graph() {
		return getGraph();
	}

	@Override
	public <R> void _reduceStartNodes(ResultNtro<R> result, Reducer<N,R> reducer) {
		if(result.hasException()) {
			return;
		}

		for(N node : getStartNodes().values()) {
			try {

				reducer.reduce(result, node);

			}catch(Throwable t) {
				
				result.registerException(t);
				return;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private GenericGraphNtro<N,E,SO> graphNtro(G graph){
		return (GenericGraphNtro<N,E,SO>) graph;
	}

	@Override
	public void setGraphName(String graphName) {
		graphNtro(graph()).setId(GraphId.fromGraphName(graphName));
	}

}
