package ca.ntro.core.graphs.generics.generic_graph;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.stream._Reducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public class GenericGraphBuilderNtro<N extends Node<N,E,SO>,
                                     E extends Edge<N,E,SO>,
                                     SO extends SearchOptionsBuilder,
                                     NB extends GenericNodeBuilder<N,E,SO,NB>,
                                     G extends GenericGraph<N,E,SO>> 

       implements     GenericGraphBuilder<N,E,SO,NB,G> {

	private G graph;
	private Map<String, N> startNodes = new HashMap<>();

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
	
	public GenericGraphBuilderNtro() {
		graph = createGraph(GraphId.newGraphId(), (GenericGraphStructure<N,E,SO>) this);
	}

	public GenericGraphBuilderNtro(String graphName) {
		graph = createGraph(GraphId.fromGraphName(graphName), (GenericGraphStructure<N,E,SO>) this);
	}
	
	protected G createGraph(GraphId id, GenericGraphStructure<N,E,SO> graphStructure) {
		return null;
	}

	@Override
	public NB addNode(String nodeId) {
		NodeIdNtro nodeIdNtro = new NodeIdNtro(nodeId);

		return addNode(nodeIdNtro);
	}
	
    @SuppressWarnings("unchecked")
	protected GenericGraphBuilder<N,E,SO,NB,GenericGraph<N,E,SO>> toGenericGraphBuilder(){
    	return (GenericGraphBuilder<N,E,SO,NB,GenericGraph<N,E,SO>>) this;
    }

	@Override
	public NB addNode(NodeId nodeId) {
		NB node = createNodeBuilder(nodeId, toGenericGraphBuilder());

		addNode(node.node());
		
		return node;
	}

	protected NB createNodeBuilder(NodeId nodeId, GenericGraphBuilder<N,E,SO,NB,GenericGraph<N,E,SO>> graphBuilder) {
		return null;
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
	public void addNode(N node) {
		if(ifNodeAlreadyExists(node)) {

			Ntro.exceptionThrower().throwException(new NodeAlreadyAddedException("Node already added: " + node.id().toKey()));

		}else {
			
			getStartNodes().put(node.id().toKey().toString(), node);

		}
	}
	
	protected boolean ifNodeAlreadyExists(N node) {
		return getGraph().reduceNodes(false, (accumulator, reachableNode) -> {
			
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
	public void setNodeClass(Class<N> nodeClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEdgeClass(Class<E> edgeClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSearchOptionsClass(Class<SO> searchOptionsClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGraphName(String string) {
		// TODO Auto-generated method stub
		
	}
}
