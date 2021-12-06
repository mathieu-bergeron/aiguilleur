package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;

public interface Graph <N extends GraphNode<N,E>,
                        E extends GraphEdge<N,E>>

       extends GenericDirectedGraph<N,E, GraphSearchOptionsBuilder> {

}
