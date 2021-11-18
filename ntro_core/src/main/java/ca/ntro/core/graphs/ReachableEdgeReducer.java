package ca.ntro.core.graphs;

import java.util.List;

public interface ReachableEdgeReducer<NV extends NodeValue, EV extends EdgeValue, R extends Object> {
	
	R reduceReachableEdge(R accumulator, List<Edge<EV>> walkedEdges, Node<NV> from, Edge<EV> edge, Node<NV> to) throws Throwable;

}
