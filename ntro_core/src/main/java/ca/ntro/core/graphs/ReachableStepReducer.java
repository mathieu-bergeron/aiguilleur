package ca.ntro.core.graphs;

import java.util.List;

public interface ReachableStepReducer<NV extends NodeValue, 
                                      EV extends EdgeValue, 
                                      N extends Node<NV>,
                                      E extends Edge<EV>,
                                      R extends Object> {
	
	
	R reduceWalkedStep(R accumulator, List<Step<NV,EV,N,E>> previousSteps, Step<NV,EV,N,E> currentStep) throws Throwable;

}
