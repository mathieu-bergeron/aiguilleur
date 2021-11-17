package ca.ntro.core.graphs;

public interface ReachableNodeReducer<NV extends NodeValue, R extends Object> {
	
	R reduceReachableNode(R accumulator, int distance, Node<NV> n) throws Throwable;

}
