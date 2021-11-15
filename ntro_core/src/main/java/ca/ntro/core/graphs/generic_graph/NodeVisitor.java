package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.exceptions.Break;

public interface NodeVisitor<N extends Node> {
	
	void visitNode(N n) throws Break;

}
