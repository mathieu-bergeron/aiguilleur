package ca.ntro.core.graphs.tests;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.EdgeName;
import ca.ntro.core.graphs.EdgeNameNtro;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;

public class GraphEdge extends EdgeNtro<GraphNode, GraphEdge, SearchOptions> {

	public GraphEdge(GraphNode from, EdgeName edgeName, GraphNode to) {
		super(from, edgeName, to);
	}

	public GraphEdge(GraphNode from, String edgeName, GraphNode to) {
		super(from, new EdgeNameNtro(Direction.FORWARD, edgeName), to);
	}

}
