package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Direction;

import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.EdgeNameNtro;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;

public class GraphEdge extends EdgeNtro<GraphNode, GraphEdge, GraphSearchOptions> {

	public GraphEdge(GraphNode from, EdgeType edgeName, GraphNode to) {
		super(from, edgeName, to);
	}

	public GraphEdge(GraphNode from, String edgeName, GraphNode to) {
		super(from, new EdgeNameNtro(Direction.FORWARD, edgeName), to);
	}

}
