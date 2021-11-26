package ca.ntro.core.graphs;

public interface NodeReducer<NV extends NodeValue, N extends Node<NV>, R extends Object> {
	
	R reduceNode(R accumulator, N n) throws Throwable;

}
