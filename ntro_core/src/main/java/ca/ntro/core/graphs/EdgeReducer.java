package ca.ntro.core.graphs;

public interface EdgeReducer<N extends Node, E extends Edge, R extends Object> {
	
	R reduceEdge(R accumulator, N from, E edge, N to) throws Throwable;

}
