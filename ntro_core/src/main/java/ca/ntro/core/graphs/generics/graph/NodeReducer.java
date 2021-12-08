package ca.ntro.core.graphs.generics.graph;

public interface NodeReducer<N extends GenericNode<N,E,SO>, 
                             E extends GenericEdge<N,E,SO>,
                             SO extends SearchOptions,
                             R extends Object> {
	
	R reduceNode(R accumulator, N n) throws Throwable;

}
