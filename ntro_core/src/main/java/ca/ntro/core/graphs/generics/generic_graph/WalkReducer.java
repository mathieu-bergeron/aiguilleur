package ca.ntro.core.graphs.generics.generic_graph;

public interface WalkReducer<N extends Node<N,E,SO>,
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder,
                             R extends Object> {

	R reduceStep(R accumulator, Walk<N,E,SO> walked, WalkId remainingWalk, N currentNode) throws Throwable;

}
