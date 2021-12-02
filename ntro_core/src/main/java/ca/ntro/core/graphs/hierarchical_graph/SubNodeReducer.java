package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.generic_graph.Walk;

public interface SubNodeReducer<N extends Node<N,E,SO>,
                                E extends Edge<N,E,SO>,
                                SO extends SearchOptionsNtro,
                                R extends Object> {
	
	R reduceSubNode(R accumulator, Walk<N,E,SO> parentNodes, N n) throws Throwable;

}
