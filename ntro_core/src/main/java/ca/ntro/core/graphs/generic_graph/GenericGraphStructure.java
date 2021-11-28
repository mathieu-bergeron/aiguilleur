package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeName;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.StepReducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface GenericGraphStructure<N extends Node<N,E,SO>,
                                       E extends Edge<N,E,SO>,
                                       SO extends SearchOptions> {

	boolean containsNode(N node);
	boolean containsEdge(E edge);

	void memorizeEdge(E edge);
	void memorizeNode(N node);

	<R> void reduceStartNodes(ResultNtro<R> result, NodeReducer<N,R> reducer);

	<R> void reduceNextEdgeIds(N fromNode, ResultNtro<R> result, EdgeNameReducer<R> reducer);

	<R> void reduceNextEdgesById(N fromNode, EdgeName edgeId, ResultNtro<R> result, StepReducer<N,E,R> reducer);
}
