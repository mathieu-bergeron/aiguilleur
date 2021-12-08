package ca.ntro.core.graphs.generics.graph;

public interface ReachableEdgeReducer<N extends GenericNode<N,E,SO>,
                                      E extends GenericEdge<N,E,SO>,
                                      SO extends SearchOptions,
                                      R extends Object> {
	
	
	// FIXME: w/ an object reachableEdge.walked() and reachableEdge.edge()
	//        we can hope to make some functional operations more generic
	R reduceEdge(R accumulator, Walk<N,E,SO> walked, E edge) throws Throwable;

}
