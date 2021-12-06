package ca.ntro.core.graphs.generics.generic_graph;

import ca.ntro.core.exceptions.Break;

public interface ReachableEdgeVisitor<N extends Node<N,E,SO>,
                                      E extends Edge<N,E,SO>,
                                      SO extends SearchOptionsBuilder> {
	
	void visitReachableEdge(Walk<N,E,SO> walked, E edge) throws Break;

}
