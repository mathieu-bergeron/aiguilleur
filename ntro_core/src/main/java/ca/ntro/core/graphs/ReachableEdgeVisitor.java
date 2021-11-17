package ca.ntro.core.graphs;

import ca.ntro.core.exceptions.Break;

public interface ReachableEdgeVisitor<N extends Node, E extends Edge> {
	
	void visitReachableEdge(int distance, N from, E edge, N to) throws Break;

}
