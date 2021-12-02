package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.Walk;

public interface ReachableNodeVisitor<N extends Node<N,E,SO>,
                                      E extends Edge<N,E,SO>,
                                      SO extends SearchOptionsNtro> {

	void visitReachableNode(Walk<N,E,SO> walked, N n) throws Throwable;

}
