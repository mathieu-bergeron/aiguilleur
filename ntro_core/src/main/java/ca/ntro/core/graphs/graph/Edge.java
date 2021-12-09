package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;

public interface Edge<N extends Node<N,E>,
                           E extends Edge<N,E>>

       extends GenericEdge<N,E, GraphSearchOptions> {

}
