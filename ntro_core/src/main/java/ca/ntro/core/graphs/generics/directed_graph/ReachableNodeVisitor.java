package ca.ntro.core.graphs.generics.directed_graph;

public interface ReachableNodeVisitor<N extends GenericNode<N,E,SO>,
                                      E extends Edge<N,E,SO>,
                                      SO extends SearchOptionsBuilder> {

	void visitReachableNode(Walk<N,E,SO> walked, N n) throws Throwable;

}
