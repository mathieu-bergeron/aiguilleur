package ca.ntro.core.graphs.generics.graph;

public interface WalkReducer<N extends GenericNode<N,E,SO>,
                             E extends GenericEdge<N,E,SO>,
                             SO extends SearchOptions,
                             R extends Object> {

	R reduceStep(R accumulator, Walk<N,E,SO> walked, WalkId remainingWalk, N currentNode) throws Throwable;

}
