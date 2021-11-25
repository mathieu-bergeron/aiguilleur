package ca.ntro.core.graphs;

import java.util.List;

import ca.ntro.core.exceptions.Break;

public interface ReachableEdgeVisitor<NV extends NodeValue, EV extends EdgeValue> {
	
	void visitReachableEdge(List<WalkedStep<NV,EV>> previousSteps, Node<NV> from, Edge<EV> edge, Node<NV> to) throws Break;

}
