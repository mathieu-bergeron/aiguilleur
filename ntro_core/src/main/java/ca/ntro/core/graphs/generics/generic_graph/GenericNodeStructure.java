package ca.ntro.core.graphs.generics.generic_graph;

import ca.ntro.core.wrappers.result.ResultNtro;

public interface GenericNodeStructure<N extends Node<N,E,SO>, 
                                      E extends Edge<N,E,SO>,
                                      SO extends SearchOptionsBuilder> {
	
	boolean isStartNode();

	<R> void reduceEdgeTypesForDirection(Direction direction, ResultNtro<R> result, EdgeTypeReducer<R> reducer);
	<R> void reduceEdgesByType(EdgeType edgeType, ResultNtro<R> result, EdgeReducer<N,E,SO,R> reducer);

	N node();

}
