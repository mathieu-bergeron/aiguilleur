package ca.ntro.core.graphs.dag;

public interface EdgeFolder<N extends Node, E extends Edge, R extends Object> {
	
	R foldEdge(R accumulator, N from, E edge, N to) throws Throwable;

}
