package ca.ntro.core.graphs;

import java.util.List;

public interface ReachableStepReducer<NV extends NodeValue, 
                                      EV extends EdgeValue, 
                                      N extends Node<NV>,
                                      E extends Edge<EV>,
                                      R extends Object> {
	
	
	R reduceWalkedStep(R accumulator, List<WalkedStep<NV,EV,N,E>> previousSteps, WalkedStep<NV,EV,N,E> currentStep) throws Throwable;

}
