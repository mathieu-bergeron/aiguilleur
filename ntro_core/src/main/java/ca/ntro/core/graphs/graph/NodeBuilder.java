package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericNodeBuilder;

public interface NodeBuilder<N extends Node<N,E>,
                                  E extends Edge<N,E>>

       extends   GenericNodeBuilder<N,E,GraphSearchOptions,NodeBuilder<N,E>> {

}
