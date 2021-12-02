package ca.ntro.core.graphs;

public interface NodeReducer<N extends Node<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder,
                             R extends Object> {
	
	R reduceNode(R accumulator, N n) throws Throwable;

}
