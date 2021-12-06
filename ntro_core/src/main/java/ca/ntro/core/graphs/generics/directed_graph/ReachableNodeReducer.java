package ca.ntro.core.graphs.generics.directed_graph;

public interface ReachableNodeReducer<N extends GenericNode<N,E,SO>,
                                      E extends Edge<N,E,SO>,
                                      SO extends SearchOptionsBuilder,
                                      R extends Object> {

	R reduceReachableNode(R accumulator, Walk<N,E,SO> walked, N n) throws Throwable;

}
