package ca.ntro.core.graphs.generic_graph.graph_strcuture;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeName;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.StepReducer;
import ca.ntro.core.graphs.generic_graph.EdgeNameReducer;
import ca.ntro.core.wrappers.result.Result;

public interface EdgesMap<N extends Node<N,E,SO>, 
                          E extends Edge<N,E,SO>,
                          SO extends SearchOptions> {

	void addEdge(E edge);

	<R> Result<R> reduceEdgeNames(R initialValue, EdgeNameReducer<R> reducer);
	<R> Result<R> reduceEdgesByName(EdgeName edgeName, R initialValue, StepReducer<N, E, R> reducer);

}
