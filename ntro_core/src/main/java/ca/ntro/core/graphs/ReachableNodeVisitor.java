package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.Walk;

public interface ReachableNodeVisitor<N extends Node,
                                      E extends Edge<N>> {

	void visitReachableNode(Walk<N,E> walked, N n) throws Throwable;

}
