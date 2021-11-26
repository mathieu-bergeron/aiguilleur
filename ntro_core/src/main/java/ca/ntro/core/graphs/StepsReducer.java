package ca.ntro.core.graphs;

import java.util.List;

public interface StepsReducer<NV extends NodeValue, EV extends EdgeValue, R extends Object> {

	R reduceStep(R accumulator, List<WalkedStep<NV,EV>> walkedSteps, List<Step> remainingSteps, Node<NV> currentNode) throws Throwable;

}
