package ca.ntro.core.graphs;

import ca.ntro.core.exceptions.Break;

public interface NodeVisitor<NV extends NodeValue, N extends Node<NV>> {
	
	void visitNode(N n) throws Break;

}
