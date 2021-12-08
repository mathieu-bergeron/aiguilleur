package ca.ntro.core.graphs.generics.graph;

public interface WalkVisitor<N extends GenericNode<N,E,SO>,
                             E extends GenericEdge<N,E,SO>,
                             SO extends SearchOptions> {

	void visitStep(Walk<N,E,SO> walked, WalkId remainingWalk, N n) throws Throwable;

}
