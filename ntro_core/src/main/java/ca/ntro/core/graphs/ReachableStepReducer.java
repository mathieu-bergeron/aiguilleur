package ca.ntro.core.graphs;

import java.util.List;

public interface ReachableStepReducer<NV extends NodeValue, EV extends EdgeValue, R extends Object> {
	
	
	R reduceWalkedStep(R accumulator, List<WalkedStep<NV,EV>> previousSteps, WalkedStep<NV, EV> currentStep) throws Throwable;

}
