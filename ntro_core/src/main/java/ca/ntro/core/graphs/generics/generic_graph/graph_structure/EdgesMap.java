package ca.ntro.core.graphs.generics.generic_graph.graph_structure;

import ca.ntro.core.graphs.generics.generic_graph.Direction;
import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.EdgeReducer;
import ca.ntro.core.graphs.generics.generic_graph.EdgeType;
import ca.ntro.core.graphs.generics.generic_graph.EdgeTypeReducer;
import ca.ntro.core.graphs.generics.generic_graph.Node;
import ca.ntro.core.graphs.generics.generic_graph.SearchOptionsBuilder;
import ca.ntro.core.graphs.generics.generic_graph.SearchOptionsNtro;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface EdgesMap<N extends Node<N,E,SO>, 
                          E extends Edge<N,E,SO>,
                          SO extends SearchOptionsBuilder> {
	
	boolean containsEdge(E edge);

	void addEdge(E edge);

	<R> void _reduceEdgeTypesForDirection(Direction direction, ResultNtro<R> result, EdgeTypeReducer<R> reducer);
	<R> void _reduceEdgesByType(EdgeType edgeName, ResultNtro<R> result, EdgeReducer<N,E,SO,R> reducer);

}
