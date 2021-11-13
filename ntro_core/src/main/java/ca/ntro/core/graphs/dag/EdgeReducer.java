package ca.ntro.core.graphs.dag;

public interface EdgeReducer<N extends Node, E extends Edge, R extends Object> {
	
	R reduce(R accumulator, N from, E edge, N to) throws Throwable;

}