package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.EdgeNtro;
import ca.ntro.core.graphs.generics.directed_graph.EdgeType;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public class GraphEdgeNtro<N extends GraphNode<N,E>,
                           E extends GraphEdge<N,E>>

       extends EdgeNtro<N,E,GraphSearchOptionsBuilder>

       implements GraphEdge<N,E> {

	public GraphEdgeNtro(N from, EdgeType edgeType, N to) {
		super(from, edgeType, to);
	}
}
