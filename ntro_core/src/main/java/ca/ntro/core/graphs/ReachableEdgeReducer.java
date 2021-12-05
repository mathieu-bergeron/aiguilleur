package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.Walk;

public interface ReachableEdgeReducer<N extends Node<N,E,SO>,
                                      E extends Edge<N,E,SO>,
                                      SO extends SearchOptionsBuilder,
                                      R extends Object> {
	
	
	// FIXME: w/ an object reachableEdge.walked() and reachableEdge.edge()
	//        we can hope to make some functional operations more generic
	R reduceEdge(R accumulator, Walk<N,E,SO> walked, E edge) throws Throwable;

}
