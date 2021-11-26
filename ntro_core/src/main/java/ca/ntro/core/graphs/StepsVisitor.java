package ca.ntro.core.graphs;

import java.util.List;

public interface StepsVisitor<NV extends NodeValue, EV extends EdgeValue> {

	void visitStep(List<WalkedStep<NV,EV>> walkedSteps, List<Step> remainingSteps, Node<NV> currentNode) throws Throwable;

}
