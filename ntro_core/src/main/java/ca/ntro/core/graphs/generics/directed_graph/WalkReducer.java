package ca.ntro.core.graphs.generics.directed_graph;

public interface WalkReducer<N extends GenericNode<N,E,SO>,
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder,
                             R extends Object> {

	R reduceStep(R accumulator, Walk<N,E,SO> walked, WalkId remainingWalk, N currentNode) throws Throwable;

}
