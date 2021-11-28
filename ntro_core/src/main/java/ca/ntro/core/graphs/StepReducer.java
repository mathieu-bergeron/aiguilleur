package ca.ntro.core.graphs;

public interface StepReducer<N extends Node<N,E>,
                             E extends Edge<N>,
                             R extends Object> {
	
	R reduceEdge(R accumulator, E edge) throws Throwable;

}
