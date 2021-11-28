package ca.ntro.core.graphs.tests;

import ca.ntro.core.graphs.EdgeName;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;

public class GraphEdge extends EdgeNtro<GraphNode, GraphEdge, SearchOptions> {

	public GraphEdge(GraphNode from, EdgeName edgeId, GraphNode to) {
		super(from, edgeId, to);
	}

}
