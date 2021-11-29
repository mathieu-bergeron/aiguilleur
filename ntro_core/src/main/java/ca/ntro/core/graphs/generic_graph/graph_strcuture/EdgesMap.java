package ca.ntro.core.graphs.generic_graph.graph_strcuture;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeName;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.generic_graph.EdgeNameReducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface EdgesMap<N extends Node<N,E,SO>, 
                          E extends Edge<N,E,SO>,
                          SO extends SearchOptions> {

	void addEdge(E edge);

	<R> void _reduceEdgeNames(ResultNtro<R> result, EdgeNameReducer<R> reducer);
	<R> void _reduceEdgesByName(EdgeName edgeName, ResultNtro<R> result, EdgeReducer<N,E,SO,R> reducer);

}
