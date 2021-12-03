package ca.ntro.core.graphs.generic_graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.EdgeTypeNtro;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeAlreadyAddedException;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeIdNtro;
import ca.ntro.core.graphs.NodeNotFoundException;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.SearchOptionsBuilder;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericGraphBuilderNtro<N extends Node<N,E,SO>,
                                              E extends Edge<N,E,SO>,
                                              SO extends SearchOptionsBuilder,
                                              G extends GenericGraph<N,E,SO>> 

       extends        GenericGraphNtro<N,E,SO>
       implements     GenericGraphBuilder<N,E,SO,G>, 
                      GenericGraph<N,E,SO> {

	private GraphId id;

	private Map<String, N> nodes = new HashMap<>();

	public GraphId getId() {
		return id;
	}

	public void setId(GraphId id) {
		this.id = id;
	}

	public Map<String, N> getNodes() {
		return nodes;
	}

	public void setNodes(Map<String, N> nodes) {
		this.nodes = nodes;
	}

	@Override
	public GraphId id() {
		return id;
	}

	@Override
	public String label() {
		return id.toHtmlId();
	}

	@Override
	protected InternalGraphWriter<N, E, SO> internalGraphWriter(){
		return new InternalGraphWriterNtro<>();
	}

	public GenericGraphBuilderNtro() {
		setId(GraphId.newGraphId());
	}

	public GenericGraphBuilderNtro(String graphName) {
		setId(GraphId.fromGraphName(graphName));
	}

	@Override
	public N addNode(String nodeId) {
		NodeIdNtro nodeIdNtro = new NodeIdNtro(nodeId);

		return addNode(nodeIdNtro);
	}
	
    @SuppressWarnings("unchecked")
	protected GenericGraphBuilder<N,E,SO,GenericGraph<N,E,SO>> toGenericGraphBuilder(){
    	return (GenericGraphBuilder<N,E,SO,GenericGraph<N,E,SO>>) this;
    }

	@Override
	public N addNode(NodeId nodeId) {
		N node = createNode(nodeId, toGenericGraphBuilder());

		addNode(node);
		
		return node;
	}

	protected abstract N createNode(NodeId nodeId, GenericGraphBuilder<N,E,SO,GenericGraph<N,E,SO>> graphBuilder);

	@Override
	public E addEdge(N fromNode, String edgeName, N toNode) {
		EdgeType edgeTypeForward = new EdgeTypeNtro(Direction.FORWARD, edgeName);
		EdgeType edgeTypeBackward = new EdgeTypeNtro(Direction.BACKWARD, edgeName);
		
		addEdge(toNode, edgeTypeBackward, fromNode);
		
		E forwardEdge = addEdge(fromNode,edgeTypeForward,toNode);

		if(!toNode.isPartOfCycle()) {
			((NodeBuilder<N,E,SO>) toNode).setIsStartNode(false);
		}
		
		return forwardEdge;
	}

	@SuppressWarnings("unchecked")
	protected E addEdge(N fromNode, EdgeType edgeType, N toNode) {

		E edge = createEdge(fromNode, edgeType, toNode);

		((NodeBuilderNtro<N,E,SO>) fromNode).addEdge(edge);
		
		return edge;
	}

	protected abstract E createEdge(N fromNode, EdgeType edgeType, N toNode);

	protected void addNode(N node) {
		if(getNodes().containsKey(node.id().toKey().toString())) {

			Ntro.exceptionThrower().throwException(new NodeAlreadyAddedException("Node already added: " + node.id().toKey()));

		}else {
			
			getNodes().put(node.id().toKey().toString(), node);

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public SO defaultSearchOptions() {
		return (SO) new SearchOptionsNtro();
	}

	@Override
	public N findNode(NodeId id) {
		N node = getNodes().get(id.toKey().toString());
		
		if(node == null) {
			Ntro.exceptionThrower().throwException(new NodeNotFoundException("Node not found for key: " + id.toKey()));
		}
		
		return node;
	}

	@Override
	protected <R> void _reduceStartNodes(ResultNtro<R> result, NodeReducer<N, E, SO, R> reducer) {
		_reduceNodes(result, reducer);
	}

	@Override
	protected <R> void _reduceNodes(ResultNtro<R> result, NodeReducer<N, E, SO, R> reducer) {
		if(result.hasException()) {
			return;
		}
		
		for(N node : nodes.values()) {
			
			try {
				
				result.registerValue(reducer.reduceNode(result.value(), node));

			}catch(Throwable t) {
				
				result.registerException(t);
				return;
			}
		}
	}

	@Override
	public <R> Result<R> reduceNodes(R initialValue, NodeReducer<N, E, SO, R> reducer) {
		ResultNtro<R> result = new ResultNtro<>(initialValue);

		_reduceNodes(result, reducer);
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public G toGraph() {
		return (G) this;
	}


}
