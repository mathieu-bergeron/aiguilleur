package ca.ntro.core.graphs.generic_graph.graph_strcuture;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptionsBuilder;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.generic_graph.EdgeTypeReducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface EdgesMap<N extends Node<N,E,SO>, 
                          E extends Edge<N,E,SO>,
                          SO extends SearchOptionsBuilder> {
	
	boolean containsEdge(E edge);

	void addEdge(E edge);

	<R> void _reduceEdgeTypesForDirection(Direction direction, ResultNtro<R> result, EdgeTypeReducer<R> reducer);
	<R> void _reduceEdgesByType(EdgeType edgeName, ResultNtro<R> result, EdgeReducer<N,E,SO,R> reducer);

}
