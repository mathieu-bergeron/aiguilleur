package ca.ntro.core.graphs.dag;

public interface NodeReducer<N extends Node, R extends Object> {
	
	R reduce(R accumulator, N n) throws Throwable;

}
