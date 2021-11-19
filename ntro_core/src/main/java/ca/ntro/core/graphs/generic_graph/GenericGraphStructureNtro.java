package ca.ntro.core.graphs.generic_graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeAlreadyAddedException;
import ca.ntro.core.graphs.EdgeId;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeAlreadyAddedException;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.ReachableEdgeReducer;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.EdgesForFromNode;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.GenericGraphStructure;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class  GenericGraphStructureNtro<NV extends NodeValue, EV extends EdgeValue> 
       implements      GenericGraphStructure<NV,EV> {
	
	private Set<String> toNodes = new HashSet<>();
	private Map<String, Node<NV>> nodes = new HashMap<>();
	private Map<String, Edge<EV>> edges = new HashMap<>();
	
	protected Set<String> getToNodes() {
		return toNodes;
	}

	protected void setToNodes(Set<String> sinkNodes) {
		this.toNodes = sinkNodes;
	}

	protected Map<String,Node<NV>> getNodes() {
		return nodes;
	}

	protected void setNodes(Map<String,Node<NV>> nodes) {
		this.nodes = nodes;
	}

	protected Map<String,Edge<EV>> getEdges() {
		return edges;
	}

	protected void setEdges(Map<String,Edge<EV>> edges) {
		this.edges = edges;
	}

	protected abstract EdgesForFromNode<NV,EV> edgesByDirection(Direction direction);

	@Override
	public void memorizeEdge(Node<NV> from, EV edgeValue, Node<NV> to) {
		addToNode(to);
		
		EdgeId edgeId = newEdgeId(from, edgeValue, to);
		
		Edge<EV> edge = new EdgeNtro<EV>(edgeId, edgeValue);

		addEdge(edge);

		_memorizeEdge(from, edge, to);
	}

	protected abstract EdgeId newEdgeId(Node<NV> from, EV edgeValue, Node<NV> to);
	
	protected abstract void _memorizeEdge(Node<NV> from, Edge<EV> edge, Node<NV> to);

	private void addToNode(Node<NV> to) {
		getToNodes().add(to.id().toKey());
	}

	@Override
	public <R> void reduceEdgeNames(Node<NV> fromNode, 
			                        Direction direction, 
			                        ResultNtro<R> result, 
			                        EdgeNameReducer<R> reducer) {

		if(result.hasException()) {
			return;
		}

		EdgesForFromNode<NV,EV> edges = edgesByDirection(direction);

		if(edges != null) {
			edges.reduceEdgeNames(fromNode, result, reducer);
		}
	}

	@Override
	public <R> void reduceEdgesByName(Node<NV> fromNode, 
			                          Direction direction, 
			                          String edgeName, 
			                          ResultNtro<R> result, 
			                          ReachableEdgeReducer<NV, EV, R> reducer) {

		if(result.hasException()) {
			return;
		}

		EdgesForFromNode<NV,EV> edges = edgesByDirection(direction);

		if(edges != null) {
			edges.reduceEdgesByName(fromNode, edgeName, result, reducer);
		}
	}

	@Override
	public void memorizeNode(Node<NV> node) {
		if(getNodes().containsKey(node.id().toKey())) {

			Ntro.exceptionThrower().throwException(new NodeAlreadyAddedException("NodeId already taken: " + node.id().toKey()));

		}else {
			
			getNodes().put(node.id().toKey(), node);
		}
	}

	private void addEdge(Edge<EV> edge) {
		if(getEdges().containsKey(edge.id().toKey())) {
			Ntro.exceptionThrower().throwException(new EdgeAlreadyAddedException("EdgeId already taken: " + edge.id().toKey()));
		}

		getEdges().put(edge.id().toKey(), edge);
	}

	@Override
	public <R> void reduceRootNodes(ResultNtro<R> result, NodeReducer<NV, R> reducer) {
		if(result.hasException()) {
			return;
		}

		for(String nodeKey : nodes.keySet()) {
			
			if(!toNodes.contains(nodeKey)) {
				
				Node<NV> node = getNodes().get(nodeKey);

				try {

					result.registerValue(reducer.reduceNode(result.value(), node));

				} catch (Throwable e) {

					result.registerException(e);
					break;
				}
			}
		}
	}
}
