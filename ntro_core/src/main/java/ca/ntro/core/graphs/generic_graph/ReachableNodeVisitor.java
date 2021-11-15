package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.exceptions.Break;

public interface ReachableNodeVisitor<N extends Node> {
	
	void visitReachableNode(int distance, N n) throws Break;

}
