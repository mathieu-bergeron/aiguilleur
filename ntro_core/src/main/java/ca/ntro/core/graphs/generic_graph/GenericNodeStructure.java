package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptionsBuilder;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface GenericNodeStructure<N extends Node<N,E,SO>, 
                                      E extends Edge<N,E,SO>,
                                      SO extends SearchOptionsBuilder> {

	<R> void reduceEdgeTypesForDirection(Direction direction, ResultNtro<R> result, EdgeTypeReducer<R> reducer);
	<R> void reduceEdgesByType(EdgeType edgeType, ResultNtro<R> result, EdgeReducer<N,E,SO,R> reducer);

}
