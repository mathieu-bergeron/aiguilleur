package ca.ntro.core.graphs;

import java.util.List;

import ca.ntro.core.exceptions.Break;

public interface ReachableStepVisitor<NV extends NodeValue, EV extends EdgeValue> {
	
	void visitReachableStep(List<WalkedStep<NV,EV>> previousSteps, WalkedStep<NV, EV> currentStep) throws Break;

}
