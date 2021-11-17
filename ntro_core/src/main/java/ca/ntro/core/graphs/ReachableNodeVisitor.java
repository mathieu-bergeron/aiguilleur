package ca.ntro.core.graphs;

import ca.ntro.core.exceptions.Break;

public interface ReachableNodeVisitor<NV extends NodeValue> {
	
	void visitReachableNode(int distance, Node<NV> n) throws Break;

}
