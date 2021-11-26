package ca.ntro.core.graphs;

import java.util.List;

import ca.ntro.core.exceptions.Break;

public interface ReachableStepVisitor<NV extends NodeValue, 
                                      EV extends EdgeValue,
                                      N extends Node<NV>,
                                      E extends Edge<EV>> {
	
	void visitReachableStep(List<WalkedStep<NV,EV,N,E>> previousSteps, WalkedStep<NV,EV,N,E> currentStep) throws Break;

}
