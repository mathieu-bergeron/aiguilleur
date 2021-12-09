package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericNodeFactory;

public interface NodeFactory<N extends Node<N,E>,
                             E extends Edge<N,E>>

       extends GenericNodeFactory<N,E,GraphSearchOptions> {

}
