package ca.ntro.core.graphs;

public interface NodeReducer<N extends Node<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsNtro,
                             R extends Object> {
	
	R reduceNode(R accumulator, N n) throws Throwable;

}
