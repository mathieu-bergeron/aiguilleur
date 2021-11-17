package ca.ntro.core.graphs;

public interface ReachableEdgeReducer<N extends Node, E extends Edge, R extends Object> {
	
	R reduceReachableEdge(R accumulator, int distance, N from, E edge, N to) throws Throwable;

}
