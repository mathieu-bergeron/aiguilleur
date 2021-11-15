package ca.ntro.core.graphs.generic_graph;

public interface NodeReducer<N extends Node, R extends Object> {
	
	R reduceNode(R accumulator, N n) throws Throwable;

}
