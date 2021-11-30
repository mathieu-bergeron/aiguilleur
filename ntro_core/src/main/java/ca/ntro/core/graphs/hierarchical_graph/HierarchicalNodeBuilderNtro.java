package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.EdgeTypeNtro;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;
import ca.ntro.core.graphs.generic_graph.EdgeTypeReducer;
import ca.ntro.core.graphs.generic_graph.graph_strcuture.EdgesByDirection;
import ca.ntro.core.graphs.generic_graph.graph_strcuture.EdgesByDirectionNtro;
import ca.ntro.core.wrappers.result.ResultNtro;

public class HierarchicalNodeBuilderNtro<N extends HierarchicalNode<N,E,SO>,
 									     E extends Edge<N,E,SO>,
 									     SO extends HierarchicalGraphSearchOptions>

       extends     HierarchicalNodeNtro<N,E,SO> 

	   implements  HierarchicalNode<N,E,SO>,
	               HierarchicalNodeBuilder<N,E,SO> {


	private EdgesByDirection<N,E,SO> edgesByDirection = new EdgesByDirectionNtro<>();

	public EdgesByDirection<N, E, SO> getEdgesByDirection() {
		return edgesByDirection;
	}

	public void setEdgesByDirection(EdgesByDirection<N, E, SO> edgesByDirection) {
		this.edgesByDirection = edgesByDirection;
	}

	public HierarchicalNodeBuilderNtro(NodeId id) {
		super(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public N toNode() {
		return (N) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addEdge(String edgeName, N to) {
		EdgeTypeNtro edgeType = new EdgeTypeNtro(Direction.FORWARD, edgeName);

		E edge = (E) new EdgeNtro<N,E,SO>(this.toNode(), edgeType, to);

		getEdgesByDirection().addEdge(edge);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addSubNode(N subNode) {
		EdgeTypeNtro edgeType = new EdgeTypeNtro(Direction.DOWN, "");

		E edge = (E) new EdgeNtro<N,E,SO>(this.toNode(), edgeType, subNode);

		getEdgesByDirection().addEdge(edge);

		((HierarchicalNodeBuilderNtro) subNode).addParentNode(this);
	}

	@SuppressWarnings("unchecked")
	protected void addParentNode(N parentNode) {
		EdgeTypeNtro edgeType = new EdgeTypeNtro(Direction.UP, "");

		E edge = (E) new EdgeNtro<N,E,SO>(this.toNode(), edgeType, parentNode);

		getEdgesByDirection().addEdge(edge);
	}

	@Override
	protected <R> void _reduceEdgeTypes(ResultNtro<R> result, EdgeTypeReducer<R> reducer) {
		getEdgesByDirection()._reduceEdgeTypes(result, reducer);
	}

	@Override
	protected <R> void _reduceEdgesByType(EdgeType edgeType, ResultNtro<R> result, EdgeReducer<N, E, SO, R> reducer) {
		getEdgesByDirection()._reduceEdgesByType(edgeType, result, reducer);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected SO defaultSearchOptions() {
		return (SO) new HierarchicalGraphSearchOptions();
	}
}
