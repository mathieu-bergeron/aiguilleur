package ca.ntro.core.graphs;

public interface EdgeReducer<N extends Node<N,E,SO>,
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsNtro,
                             R extends Object> {

	R reduceEdge(R accumulator, E edge) throws Throwable;

}
