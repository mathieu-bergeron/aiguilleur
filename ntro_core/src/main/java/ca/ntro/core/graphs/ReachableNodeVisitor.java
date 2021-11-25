package ca.ntro.core.graphs;

import java.util.List;

public interface ReachableNodeVisitor<NV extends NodeValue, EV extends EdgeValue> {

	void visitReachableNode(List<WalkedStep<NV,EV>> walkedSteps, Node<NV> n) throws Throwable;

}
