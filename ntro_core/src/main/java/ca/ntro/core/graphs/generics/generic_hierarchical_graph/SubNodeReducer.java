package ca.ntro.core.graphs.generics.generic_hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.Node;
import ca.ntro.core.graphs.generics.generic_graph.Walk;

public interface SubNodeReducer<N extends Node<N,E,SO>,
                                E extends Edge<N,E,SO>,
                                SO extends HierarchicalSearchOptionsBuilder,
                                R extends Object> {
	
	R reduceSubNode(R accumulator, Walk<N,E,SO> parentNodes, N n) throws Throwable;

}
