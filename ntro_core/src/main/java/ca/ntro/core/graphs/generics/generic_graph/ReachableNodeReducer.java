package ca.ntro.core.graphs.generics.generic_graph;

public interface ReachableNodeReducer<N extends Node<N,E,SO>,
                                      E extends Edge<N,E,SO>,
                                      SO extends SearchOptionsBuilder,
                                      R extends Object> {

	R reduceReachableNode(R accumulator, Walk<N,E,SO> walked, N n) throws Throwable;

}
