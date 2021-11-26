package ca.ntro.core.graphs;

import java.util.List;

public interface StepsReducer<NV extends NodeValue, 
                              EV extends EdgeValue, 
                              N extends Node<NV>,
                              E extends Edge<EV>,
                              R extends Object> {

	R reduceStep(R accumulator, List<WalkedStep<NV,EV,N,E>> walkedSteps, List<Step> remainingSteps, Node<NV> currentNode) throws Throwable;

}
