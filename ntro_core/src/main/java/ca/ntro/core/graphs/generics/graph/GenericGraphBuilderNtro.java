package ca.ntro.core.graphs.generics.graph;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.common.EdgeTypeNtro;
import ca.ntro.core.graphs.common.NodeAlreadyAddedException;
import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.common.NodeIdNtro;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public abstract class GenericGraphBuilderNtro<N extends GenericNode<N,E,SO>,
                                              E extends GenericEdge<N,E,SO>,
                                              SO extends SearchOptions,
                                              NB extends GenericNodeBuilder<N,E,SO,NB>,
                                              GO extends GraphWriterOptions,
                                              G extends GenericGraph<N,E,SO,GO>> 

       implements     GenericGraphBuilder<N,E,SO,NB,GO,G> {
	
	private GenericNodeFactory<N,E,SO> nodeFactory;
	private GenericEdgeFactory<N,E,SO> edgeFactory;
	
	private G graph;
	private Map<String, N> startNodes = new HashMap<>();
	
	protected abstract G newGraphInstance();
	protected abstract NB newNodeBuilderInstance();
	
	public GenericNodeFactory<N, E, SO> getNodeFactory() {
		return nodeFactory;
	}

	public void setNodeFactory(GenericNodeFactory<N, E, SO> nodeFactory) {
		this.nodeFactory = nodeFactory;
	}

	public GenericEdgeFactory<N, E, SO> getEdgeFactory() {
		return edgeFactory;
	}

	public void setEdgeFactory(GenericEdgeFactory<N, E, SO> edgeFactory) {
		this.edgeFactory = edgeFactory;
	}

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
		N node = getNodeFactory().newInstance();
		
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
		nodeNtro(node).setGraph(getGraph());

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
		
		E edge = edgeFactory.newInstance();

		edgeNtro(edge).setFrom(fromNode.node());
		edgeNtro(edge).setTo(toNode.node());
		edgeNtro(edge).setEdgeType(edgeType);
		
		return edge;
	}

	protected void memorizeNode(N node) {
		if(ifNodeAlreadyExists(node)) {

			Ntro.exceptions().throwException(new NodeAlreadyAddedException("Node already added: " + node.id().toKey()));

		}else {
			
			getStartNodes().put(node.id().toKey().toString(), node);

		}
	}

	protected boolean ifNodeAlreadyExists(N node) {
		return graph().nodes().ifSome(n -> n.equals(node));
	}

	@Override
	public G graph() {
		return getGraph();
	}

	@Override
	public Stream<N> startNodes(){
		return new StreamNtro<N>() {

			@Override
			public void _forEach(Visitor<N> visitor) throws Throwable {
				for(N node : getStartNodes().values()) {

					visitor.visit(node);
				}
			}
		};
	}
	
	@SuppressWarnings("unchecked")
	private GenericGraphNtro<N,E,SO,GO> graphNtro(G graph){
		return (GenericGraphNtro<N,E,SO,GO>) graph;
	}

	@Override
	public void setGraphName(String graphName) {
		graphNtro(graph()).setId(GraphId.fromGraphName(graphName));
	}

}
