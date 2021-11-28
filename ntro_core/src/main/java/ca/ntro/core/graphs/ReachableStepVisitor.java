package ca.ntro.core.graphs;

import java.util.List;

import ca.ntro.core.exceptions.Break;

public interface ReachableStepVisitor<NV extends NodeValue, 
                                      EV extends EdgeValue,
                                      N extends Node<NV>,
                                      E extends Edge<EV>> {
	
	void visitReachableStep(List<Step<NV,EV,N,E>> previousSteps, Step<NV,EV,N,E> currentStep) throws Break;

}
