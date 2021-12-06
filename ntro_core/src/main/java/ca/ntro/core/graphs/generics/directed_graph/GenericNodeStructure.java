package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.wrappers.result.ResultNtro;

public interface GenericNodeStructure<N extends GenericNode<N,E,SO>, 
                                      E extends Edge<N,E,SO>,
                                      SO extends SearchOptionsBuilder> {

	<R> void reduceEdgeTypesForDirection(Direction direction, ResultNtro<R> result, EdgeTypeReducer<R> reducer);
	<R> void reduceEdgesByType(EdgeType edgeType, ResultNtro<R> result, EdgeReducer<N,E,SO,R> reducer);

}
