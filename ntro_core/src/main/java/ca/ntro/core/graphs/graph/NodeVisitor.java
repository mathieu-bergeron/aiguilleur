package ca.ntro.core.graphs.graph;

import ca.ntro.core.exceptions.Break;

public interface NodeVisitor<N extends Node> {
	
	void visitNode(N n) throws Break;

}
