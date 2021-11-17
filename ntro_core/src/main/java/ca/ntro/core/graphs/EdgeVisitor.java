package ca.ntro.core.graphs;

import ca.ntro.core.exceptions.Break;

public interface EdgeVisitor<N extends Node, E extends Edge> {
	
	void visitEdge(N from, E edge, N to) throws Break;

}
