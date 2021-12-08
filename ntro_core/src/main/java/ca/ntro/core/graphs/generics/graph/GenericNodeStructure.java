package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface GenericNodeStructure<N extends GenericNode<N,E,SO>, 
                                      E extends GenericEdge<N,E,SO>,
                                      SO extends SearchOptions> {
	
	boolean isStartNode();

	<R> void reduceEdgeTypesForDirection(Direction direction, ResultNtro<R> result, EdgeTypeReducer<R> reducer);
	<R> void reduceEdgesByType(EdgeType edgeType, ResultNtro<R> result, EdgeReducer<N,E,SO,R> reducer);

	N node();

}
