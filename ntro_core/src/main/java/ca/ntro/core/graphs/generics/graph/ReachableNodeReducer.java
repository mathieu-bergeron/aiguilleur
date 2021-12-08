package ca.ntro.core.graphs.generics.graph;

public interface ReachableNodeReducer<N extends GenericNode<N,E,SO>,
                                      E extends GenericEdge<N,E,SO>,
                                      SO extends SearchOptions,
                                      R extends Object> {

	R reduceReachableNode(R accumulator, Walk<N,E,SO> walked, N n) throws Throwable;

}
