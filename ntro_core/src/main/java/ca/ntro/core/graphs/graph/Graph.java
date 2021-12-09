package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericGraph;

public interface Graph <N extends Node<N,E>,
                        E extends Edge<N,E>>

       extends GenericGraph<N,E, GraphSearchOptions> {

}
