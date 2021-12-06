package ca.ntro.core.graphs.generics.hierarchical_directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericNode;
import ca.ntro.core.graphs.generics.directed_graph.Walk;

public interface SubNodeReducer<N extends GenericNode<N,E,SO>,
                                E extends Edge<N,E,SO>,
                                SO extends HierarchicalGraphSearchOptionsBuilder,
                                R extends Object> {
	
	R reduceSubNode(R accumulator, Walk<N,E,SO> parentNodes, N n) throws Throwable;

}
