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

public abstract class HierarchicalNodeBuilderNtro<N extends HierarchicalNode<N,E,SO>,
 									              E extends Edge<N,E,SO>,
 									              SO extends HierarchicalGraphSearchOptionsBuilder>

       extends        HierarchicalNodeNtro<N,E,SO> 

	   implements     HierarchicalNode<N,E,SO>,
	                  HierarchicalNodeBuilder<N,E,SO> {


	private boolean isStartNode = true;
	private HierarchicalGraphBuilder<N,E,SO> graphBuilder;
	private EdgesByDirection<N,E,SO> edgesByDirection = new EdgesByDirectionNtro<>();

	public EdgesByDirection<N, E, SO> getEdgesByDirection() {
		return edgesByDirection;
	}

	public void setEdgesByDirection(EdgesByDirection<N, E, SO> edgesByDirection) {
		this.edgesByDirection = edgesByDirection;
	}

	public HierarchicalGraphBuilder<N, E, SO> getGraphBuilder() {
		return graphBuilder;
	}

	public void setGraphBuilder(HierarchicalGraphBuilder<N, E, SO> graphBuilder) {
		this.graphBuilder = graphBuilder;
	}
	
	@Override
	public void setIsStartNode(boolean isStartNode) {
		this.isStartNode = isStartNode;
	}

	public boolean getIsStartNode() {
		return isStartNode;
	}


	public HierarchicalNodeBuilderNtro(NodeId id, HierarchicalGraphBuilder<N,E,SO> graphBuilder) {
		super(id);
		setGraphBuilder(graphBuilder);
	}

	@SuppressWarnings("unchecked")
	@Override
	public N toNode() {
		return (N) this;
	}

	@Override
	public E addEdge(String edgeName, N toNode) {
		return getGraphBuilder().addEdge(this.toNode(), edgeName, toNode);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addSubNode(N subNode) {
		EdgeTypeNtro edgeType = new EdgeTypeNtro(Direction.DOWN, "");

		E edge = (E) new EdgeNtro<N,E,SO>(this.toNode(), edgeType, subNode);

		getEdgesByDirection().addEdge(edge);

		((HierarchicalNodeBuilderNtro<N,E,SO>) subNode).addParentNode(this.toNode());
	}

	@SuppressWarnings("unchecked")
	protected void addParentNode(N parentNode) {
		EdgeTypeNtro edgeType = new EdgeTypeNtro(Direction.UP, "");

		E edge = (E) new EdgeNtro<N,E,SO>(this.toNode(), edgeType, parentNode);

		getEdgesByDirection().addEdge(edge);
	}

	@Override
	protected <R> void _reduceEdgeTypesForDirection(Direction direction, ResultNtro<R> result, EdgeTypeReducer<R> reducer) {
		getEdgesByDirection()._reduceEdgeTypesForDirection(direction, result, reducer);
	}

	@Override
	protected <R> void _reduceEdgesByType(EdgeType edgeType, ResultNtro<R> result, EdgeReducer<N, E, SO, R> reducer) {
		getEdgesByDirection()._reduceEdgesByType(edgeType, result, reducer);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected SO defaultSearchOptions() {
		return (SO) new HierarchicalGraphSearchOptionsBuilderNtro();
	}

	@Override
	public boolean isStartNode() {
		return getIsStartNode();
	}

}
