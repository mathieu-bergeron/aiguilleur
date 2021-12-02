package ca.ntro.core.graphs;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.generic_graph.Walk;

public interface ReachableEdgeVisitor<N extends Node<N,E,SO>,
                                      E extends Edge<N,E,SO>,
                                      SO extends SearchOptionsNtro> {
	
	void visitReachableEdge(Walk<N,E,SO> walked, E edge) throws Break;

}
