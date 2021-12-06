package ca.ntro.core.graphs.generics.directed_graph;

public interface WalkVisitor<N extends GenericNode<N,E,SO>,
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder> {

	void visitStep(Walk<N,E,SO> walked, WalkId remainingWalk, N n) throws Throwable;

}
