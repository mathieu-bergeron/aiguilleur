package ca.ntro.core.graphs;

import ca.ntro.core.exceptions.Break;

public interface NodeVisitor<NV extends NodeValue> {
	
	void visitNode(Node<NV> n) throws Break;

}
