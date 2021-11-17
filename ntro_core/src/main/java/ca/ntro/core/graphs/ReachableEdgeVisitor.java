package ca.ntro.core.graphs;

import ca.ntro.core.exceptions.Break;

public interface ReachableEdgeVisitor<NV extends NodeValue, EV extends EdgeValue> {
	
	void visitReachableEdge(int distance, Node<NV> from, Edge<EV> edge, Node<NV> to) throws Break;

}
