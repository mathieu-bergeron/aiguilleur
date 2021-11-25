package ca.ntro.core.graphs;

import java.util.List;

public interface ReachableNodeReducer<NV extends NodeValue, EV extends EdgeValue, R extends Object> {
	
	R reduceReachableNode(R accumulator, List<WalkedStep<NV,EV>> walkedSteps, Node<NV> n) throws Throwable;

}
