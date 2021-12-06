package ca.ntro.core.graphs.generics.generic_graph;

public interface EdgeReducer<N extends Node<N,E,SO>,
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder,
                             R extends Object> {

	R reduceEdge(R accumulator, E edge) throws Throwable;

}
