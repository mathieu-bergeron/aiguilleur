package ca.ntro.core.graphs.generic_graph;

public interface ReachableNodeReducer<N extends Node, R extends Object> {
	
	R reduce(R accumulator, int distance, N n) throws Throwable;

}
