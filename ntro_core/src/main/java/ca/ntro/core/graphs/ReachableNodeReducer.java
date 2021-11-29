package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.Walk;

public interface ReachableNodeReducer<N extends Node<N,E,SO>,
                                      E extends Edge<N,E,SO>,
                                      SO extends SearchOptions,
                                      R extends Object> {

	R reduceReachableNode(R accumulator, Walk<N,E,SO> walked, N n) throws Throwable;

}
