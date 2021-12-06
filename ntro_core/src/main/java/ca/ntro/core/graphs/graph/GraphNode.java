package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.generic_graph.Node;

public interface GraphNode<N extends GraphNode<N,E>,
                           E extends GraphEdge<N,E>>

       extends Node<N,E,GraphSearchOptionsBuilder> {

}
