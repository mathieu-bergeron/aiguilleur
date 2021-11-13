package ca.ntro.core.graphs.dag;

public interface NodeReducer<N extends Node, R extends Object> {
	
	R foldNode(R accumulator, N n) throws Throwable;

}
