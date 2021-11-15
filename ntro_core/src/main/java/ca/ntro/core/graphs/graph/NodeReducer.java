package ca.ntro.core.graphs.graph;

public interface NodeReducer<N extends Node, R extends Object> {
	
	R reduce(R accumulator, N n) throws Throwable;

}
