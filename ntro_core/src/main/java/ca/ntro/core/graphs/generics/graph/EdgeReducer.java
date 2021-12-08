package ca.ntro.core.graphs.generics.graph;

public interface EdgeReducer<N extends GenericNode<N,E,SO>,
                             E extends GenericEdge<N,E,SO>,
                             SO extends SearchOptions,
                             R extends Object> {

	R reduceEdge(R accumulator, E edge) throws Throwable;

}
