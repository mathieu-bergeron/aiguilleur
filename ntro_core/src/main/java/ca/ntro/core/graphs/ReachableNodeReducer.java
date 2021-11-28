package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.Walk;

public interface ReachableNodeReducer<N extends Node,
                                      E extends Edge<N>,
                                      R extends Object> {

	R reduceReachableNode(R accumulator, Walk<N,E> walked, N n) throws Throwable;

}
