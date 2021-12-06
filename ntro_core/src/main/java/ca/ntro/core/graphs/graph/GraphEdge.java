package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.generic_graph.Edge;

public interface GraphEdge<N extends GraphNode<N,E>,
                           E extends GraphEdge<N,E>>

       extends Edge<N,E, GraphSearchOptionsBuilder> {

}
