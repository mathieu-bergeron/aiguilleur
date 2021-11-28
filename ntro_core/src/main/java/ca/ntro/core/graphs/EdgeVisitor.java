package ca.ntro.core.graphs;

import ca.ntro.core.exceptions.Break;

public interface EdgeVisitor<N extends Node, E extends Edge<N>> {
	
	void visitEdge(E edge) throws Break;

}
