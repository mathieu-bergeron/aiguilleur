package ca.ntro.core.graphs.generic_graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeAlreadyAddedException;
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
	
	private Set<String> rootNodes = new HashSet<>();
	private Map<String, Node<NV>> nodes = new HashMap<>();
	private Map<String, Edge<EV>> edges = new HashMap<>();
	
	protected Map<String,Node<NV>> getNodes() {
		return nodes;
	}

	protected void setNodes(Map<String,Node<NV>> nodes) {
		this.nodes = nodes;
	}

	protected Set<String> getRootNodes() {
		return rootNodes;
	}

	protected void setRootNodes(Set<String> rootNodes) {
		this.rootNodes = rootNodes;
	}

	protected Map<String,Edge<EV>> getEdges() {
		return edges;
	}

	protected void setEdges(Map<String,Edge<EV>> edges) {
		this.edges = edges;
	}

	protected abstract EdgesForFromNode<NV,EV> edgesByDirection(Direction direction);

	@Override
	public void addEdge(Direction direction, Node<NV> from, Edge<EV> edge, Node<NV> to) {
		EdgesForFromNode<NV,EV> edges = edgesByDirection(direction);

		if(edges != null) {
			edges.addEdge(from, edge, to);
		}
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
	
	@Override
	public void memorizeRootNode(Node<NV> node) {
		getRootNodes().add(node.id().toKey());
	}

	@Override
	public void forgetRootNode(Node<NV> node) {
		getRootNodes().remove(node.id().toKey());
	}

	@Override
	public void memorizeEdge(Edge<EV> edge) {
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

		for(String rootNodeKey : getRootNodes()) {
			
			Node<NV> node = getNodes().get(rootNodeKey);

			try {

				result.registerValue(reducer.reduceNode(result.value(), node));

			} catch (Throwable e) {

				result.registerException(e);
				break;
			}
		}
	}
}
