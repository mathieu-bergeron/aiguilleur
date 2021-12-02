package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.Walk;
import ca.ntro.core.graphs.generic_graph.WalkId;

public interface WalkReducer<N extends Node<N,E,SO>,
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsNtro,
                             R extends Object> {

	R reduceStep(R accumulator, Walk<N,E,SO> walked, WalkId remainingWalk, N currentNode) throws Throwable;

}
