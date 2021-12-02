package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.Walk;

public interface ReachableStepReducer<N extends Node<N,E,SO>,
                                      E extends Edge<N,E,SO>,
                                      SO extends SearchOptionsNtro,
                                      R extends Object> {
	
	
	R reduceWalkedStep(R accumulator, Walk<N,E,SO> walked, E edge) throws Throwable;

}
