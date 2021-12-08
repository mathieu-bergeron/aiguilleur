package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericNode;
import ca.ntro.core.graphs.generics.graph.Walk;

public interface SubNodeReducer<N extends GenericNode<N,E,SO>,
                                E extends GenericEdge<N,E,SO>,
                                SO extends HierarchicalSearchOptions,
                                R extends Object> {
	
	R reduceSubNode(R accumulator, Walk<N,E,SO> parentNodes, N n) throws Throwable;

}
