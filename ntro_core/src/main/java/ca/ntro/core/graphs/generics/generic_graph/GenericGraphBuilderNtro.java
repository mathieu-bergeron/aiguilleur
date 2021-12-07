package ca.ntro.core.graphs.generics.generic_graph;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.initialization.Factory;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.stream._Reducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericGraphBuilderNtro<N extends Node<N,E,SO>,
                                              E extends Edge<N,E,SO>,
                                              SO extends SearchOptionsBuilder,
                                              NB extends GenericNodeBuilder<N,E,SO,NB>,
                                              G extends GenericGraph<N,E,SO>> 

       implements     GenericGraphBuilder<N,E,SO,NB,G> {
	
	private Class<N> nodeClass;
	private Class<E> edgeClass;

	private GenericGraphNtro<N,E,SO> graph;
	private Map<String, N> startNodes = new HashMap<>();
	
	protected abstract GenericGraphNtro<N,E,SO> createGraph();
	protected abstract GenericNodeBuilderNtro<N,E,SO,NB> createNodeBuilder();

	public Map<String, N> getStartNodes() {
		return startNodes;
	}

	public void setStartNodes(Map<String, N> nodes) {
		this.startNodes = nodes;
	}

	public GenericGraphNtro<N, E, SO> getGraph() {
		return graph;
	}
	public void setGraph(GenericGraphNtro<N, E, SO> graph) {
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

	void initialize() {
		graph = createGraph();
		graph.setGraphStructure(this);
	}

	@Override
	public NB addNode(String nodeId) {
		NodeIdNtro nodeIdNtro = new NodeIdNtro(nodeId);

		return addNode(nodeIdNtro);
	}

	@Override
	public NB addNode(NodeId nodeId) {
		NB nodeBuilder = createNodeBuilder(nodeId);

		addNode(nodeBuilder.node());
		
		return nodeBuilder;
	}

	protected NB createNodeBuilder(NodeId nodeId) {
		GenericNodeBuilderNtro<N,E,SO,NB> nodeBuilder = createNodeBuilder();
		
		N node = Factory.newInstance(getNodeClass());

		((GenericNodeNtro<N,E,SO>)node).setNodeId(nodeId);
		((GenericNodeNtro<N,E,SO>)node).setNodeStructure(nodeBuilder);

		nodeBuilder.setGraphBuilder(this);
		nodeBuilder.setNode(node);
		
		return (NB) nodeBuilder;
	}

	@Override
	public E addEdge(NB fromNode, String edgeName, NB toNode) {
		EdgeType edgeTypeForward = new EdgeTypeNtro(Direction.FORWARD, edgeName);
		EdgeType edgeTypeBackward = new EdgeTypeNtro(Direction.BACKWARD, edgeName);
		
		addEdge(toNode, edgeTypeBackward, fromNode);
		
		E forwardEdge = addEdge(fromNode,edgeTypeForward,toNode);

		if(!toNode.node().isPartOfCycle()) {
			toNode.setIsStartNode(false);
			getStartNodes().remove(toNode.node().id().toKey().toString());
		}

		return forwardEdge;
	}

	protected E addEdge(NB fromNode, EdgeType edgeType, NB toNode) {

		E edge = createEdge(fromNode, edgeType, toNode);

		fromNode.addEdge(edge);
		
		return edge;
	}

	protected E createEdge(NB fromNode, EdgeType edgeType, NB toNode) {
		return null;
	}

	@Override
	public NB addNode(N node) {
		GenericNodeBuilderNtro<N,E,SO,NB> builder = createNodeBuilder();
		builder.setGraphBuilder(this);
		builder.setNode(node);
		
		((GenericNodeNtro<N,E,SO>)node).setNodeStructure(builder);
		
		memorizeNode(node);
		
		return (NB) builder;
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

	@SuppressWarnings("unchecked")
	@Override
	public G graph() {
		return (G) getGraph();
	}

	@Override
	public <R> void _reduceStartNodes(ResultNtro<R> result, _Reducer<N,R> reducer) {
		if(result.hasException()) {
			return;
		}

		for(N node : startNodes.values()) {
			try {

				reducer._reduce(result, node);

			}catch(Throwable t) {
				
				result.registerException(t);
				return;
			}
		}
		
	}

	@Override
	public void setGraphName(String graphName) {
		((GenericGraphNtro<N,E,SO>) graph).setId(GraphId.fromGraphName(graphName));
	}

}
