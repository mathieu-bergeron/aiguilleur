package ca.ntro.core.graphs.generic_graph;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.EdgeTypeNtro;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeAlreadyAddedException;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeIdNtro;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.SearchOptionsBuilder;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericGraphBuilderNtro<N extends Node<N,E,SO>,
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
	
	protected abstract G createGraph(GraphId id, GenericGraphStructure<N,E,SO> graphStructure);

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

		addNode(node.toNode());
		
		return node;
	}

	protected abstract NB createNodeBuilder(NodeId nodeId, GenericGraphBuilder<N,E,SO,NB,GenericGraph<N,E,SO>> graphBuilder);

	@Override
	public E addEdge(NB fromNode, String edgeName, NB toNode) {
		EdgeType edgeTypeForward = new EdgeTypeNtro(Direction.FORWARD, edgeName);
		EdgeType edgeTypeBackward = new EdgeTypeNtro(Direction.BACKWARD, edgeName);
		
		addEdge(toNode, edgeTypeBackward, fromNode);
		
		E forwardEdge = addEdge(fromNode,edgeTypeForward,toNode);

		if(!toNode.isPartOfCycle()) {
			toNode.setIsStartNode(false);
			getStartNodes().remove(toNode.id().toKey().toString());
		}

		return forwardEdge;
	}

	protected E addEdge(NB fromNode, EdgeType edgeType, NB toNode) {

		E edge = createEdge(fromNode, edgeType, toNode);

		fromNode.addEdge(edge);
		
		return edge;
	}

	protected abstract E createEdge(NB fromNode, EdgeType edgeType, NB toNode);

	protected void addNode(N node) {
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
	public G asGraph() {
		return getGraph();
	}
}
