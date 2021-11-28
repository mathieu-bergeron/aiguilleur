package ca.ntro.core.graphs;

import java.util.List;

public interface ReachableNodeReducer<NV extends NodeValue, 
                                      EV extends EdgeValue, 
                                      N extends Node<NV>,
                                      E extends Edge<EV>,
                                      R extends Object> {
	
	R reduceReachableNode(R accumulator, List<Step<NV,EV,N,E>> walkedSteps, N n) throws Throwable;

}
