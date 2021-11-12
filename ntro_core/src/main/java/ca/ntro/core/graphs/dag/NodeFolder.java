package ca.ntro.core.graphs.dag;

public interface NodeFolder<N extends Node, R extends Object> {
	
	R foldNode(R accumulator, N n) throws Throwable;

}
