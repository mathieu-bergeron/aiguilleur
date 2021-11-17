package ca.ntro.core.graphs;

public interface ReachableEdgeReducer<NV extends NodeValue, EV extends EdgeValue, R extends Object> {
	
	R reduceReachableEdge(R accumulator, int distance, Node<NV> from, Edge<EV> edge, Node<NV> to) throws Throwable;

}
