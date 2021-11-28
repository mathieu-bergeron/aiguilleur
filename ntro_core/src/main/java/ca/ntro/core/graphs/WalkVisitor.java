package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.Walk;
import ca.ntro.core.graphs.generic_graph.WalkId;

public interface WalkVisitor<N extends Node<N,E,SO>,
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptions> {

	void visitStep(Walk<N,E> walked, WalkId remainingWalk, N n) throws Throwable;

}
