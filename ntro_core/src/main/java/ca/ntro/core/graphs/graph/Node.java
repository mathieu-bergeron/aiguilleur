package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericNode;

public interface Node<N extends Node<N,E>,
                           E extends Edge<N,E>>

       extends GenericNode<N,E,GraphSearchOptions> {

}
