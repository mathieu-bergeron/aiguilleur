package ca.ntro.core.graphs.generic_graph;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeId;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.ReachableEdgeReducer;
import ca.ntro.core.graphs.Step;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.EdgesForFromNode;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.GenericGraphStructure;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class  GenericGraphStructureNtro<NV extends NodeValue, EV extends EdgeValue> 
       implements      GenericGraphStructure<NV,EV> {
	
	private Map<String, Node<NV>> nodes = new HashMap<>();
	private Map<String, Edge<EV>> edges = new HashMap<>();
	
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
	public Edge<EV> createEdge(Node<NV> from, EV edgeValue, Node<NV> to) {

		EdgeId edgeId = directedEdgeId(from, edgeValue, to);
		
		Edge<EV> edge = new EdgeNtro<EV>(edgeId, edgeValue);

		return edge;
	}

	protected abstract EdgeId directedEdgeId(Node<NV> from, EV edgeValue, Node<NV> to);
	
	@Override
	public void memorizeEdge(Node<NV> from, Edge<EV> edge, Node<NV> to) {
		getEdges().put(edge.id().toKey(), edge);

		memorizeDirectedEdge(from, edge, to);
	}

	protected abstract void memorizeDirectedEdge(Node<NV> from, Edge<EV> edge, Node<NV> to);

	@Override
	public <R> void reduceNextSteps(Node<NV> fromNode, 
			                        ResultNtro<R> result, 
			                        StepReducer<R> reducer) {

		if(result.hasException()) {
			return;
		}

		for(Direction direction : Direction.values()) {

			EdgesForFromNode<NV,EV> edges = edgesByDirection(direction);

			if(edges != null) {
				edges.reduceNextSteps(fromNode, direction, result, reducer);
			}
		}
	}

	@Override
	public <R> void walkStep(Node<NV> fromNode, 
						     Step step,
							 ResultNtro<R> result, 
							 ReachableEdgeReducer<NV, EV, R> reducer) {

		if(result.hasException()) {
			return;
		}

		EdgesForFromNode<NV,EV> edges = edgesByDirection(step.direction());

		if(edges != null) {
			edges.walkStep(fromNode, step, result, reducer);
		}
	}

	@Override
	public void memorizeNode(Node<NV> node) {
		getNodes().put(node.id().toKey(), node);

		if(getNodes().containsKey(node.id().toKey())) {
			
			throw new RuntimeException("TODO");


		}else {

			throw new RuntimeException("TODO");
			
		}
	}

	@Override
	public <R> void reduceStartNodes(ResultNtro<R> result, NodeReducer<NV, R> reducer) {
		if(result.hasException()) {
			return;
		}

		for(String nodeKey : nodes.keySet()) {

			Node<NV> node = getNodes().get(nodeKey);

			try {

				result.registerValue(reducer.reduceNode(result.value(), node));

			} catch (Throwable e) {

				result.registerException(e);
				return;
			}
		}
	}

	@Override
	public boolean containsNode(Node<NV> node) {
		return getNodes().containsKey(node.id().toKey());
	}

	@Override
	public boolean containsEdge(Edge<EV> edge) {
		return getEdges().containsKey(edge.id().toKey());
	}

	@Override
	public Node<NV> createNode(NV nodeValue) {

		NodeId nodeId = new NodeId(nodeValue.name().toKey());

		Node<NV> node = new NodeNtro<>(nodeId, nodeValue);
		
		return node;
	}
}
