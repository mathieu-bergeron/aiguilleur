package ca.ntro.core.graphs;

import ca.ntro.core.exceptions.Break;

public interface EdgeVisitor<NV extends NodeValue, EV extends EdgeValue, N extends Node<NV>, E extends Edge<EV>> {
	
	void visitEdge(N from, E edge, N to) throws Break;

}
