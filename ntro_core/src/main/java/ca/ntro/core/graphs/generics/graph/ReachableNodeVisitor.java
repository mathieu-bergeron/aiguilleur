package ca.ntro.core.graphs.generics.graph;

public interface ReachableNodeVisitor<N extends GenericNode<N,E,SO>,
                                      E extends GenericEdge<N,E,SO>,
                                      SO extends SearchOptions> {

	void visitReachableNode(Walk<N,E,SO> walked, N n) throws Throwable;

}
