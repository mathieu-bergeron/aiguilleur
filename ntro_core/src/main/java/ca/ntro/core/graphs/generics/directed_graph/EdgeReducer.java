package ca.ntro.core.graphs.generics.directed_graph;

public interface EdgeReducer<N extends GenericNode<N,E,SO>,
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder,
                             R extends Object> {

	R reduceEdge(R accumulator, E edge) throws Throwable;

}
