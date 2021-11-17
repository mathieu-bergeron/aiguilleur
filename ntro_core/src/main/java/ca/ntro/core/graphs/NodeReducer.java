package ca.ntro.core.graphs;

public interface NodeReducer<N extends Node, R extends Object> {
	
	R reduceNode(R accumulator, N n) throws Throwable;

}
