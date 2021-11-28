package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.Walk;

public interface ReachableStepReducer<N extends Node<N,E>,
                                      E extends Edge<N>,
                                      R extends Object> {
	
	
	R reduceWalkedStep(R accumulator, Walk<N,E> walked, E edge) throws Throwable;

}
