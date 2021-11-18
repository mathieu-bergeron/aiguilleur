package ca.ntro.core.graphs;

import java.util.List;

public interface ReachableNodeReducer<NV extends NodeValue, EV extends EdgeValue, R extends Object> {
	
	R reduceReachableNode(R accumulator, List<Edge<EV>> walkedEdges, Node<NV> n) throws Throwable;

}
