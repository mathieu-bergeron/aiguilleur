package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericNode;

public interface GraphNode<N extends GraphNode<N,E>,
                           E extends GraphEdge<N,E>>

       extends GenericNode<N,E,GraphSearchOptionsBuilder> {

}
