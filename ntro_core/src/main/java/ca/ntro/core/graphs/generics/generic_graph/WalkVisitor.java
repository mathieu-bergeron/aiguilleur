package ca.ntro.core.graphs.generics.generic_graph;

public interface WalkVisitor<N extends Node<N,E,SO>,
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder> {

	void visitStep(Walk<N,E,SO> walked, WalkId remainingWalk, N n) throws Throwable;

}
