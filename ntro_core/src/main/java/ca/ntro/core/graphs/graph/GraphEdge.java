package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public interface GraphEdge<N extends GraphNode<N,E>,
                           E extends GraphEdge<N,E>>

       extends Edge<N,E, GraphSearchOptionsBuilder> {

}
