package ca.ntro.core.graphs.generics.directed_graph;

public interface NodeReducer<N extends GenericNode<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder,
                             R extends Object> {
	
	R reduceNode(R accumulator, N n) throws Throwable;

}
