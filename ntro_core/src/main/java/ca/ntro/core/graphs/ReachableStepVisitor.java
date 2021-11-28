package ca.ntro.core.graphs;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.generic_graph.Walk;

public interface ReachableStepVisitor<N extends Node<N,E>,
                                      E extends Edge<N>> {
	
	void visitReachableStep(Walk<N,E> walked, E edge) throws Break;

}
