package ca.ntro.core.graphs.generics.graph;


import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.graph_writer.GraphWriter;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.wrappers.result.Result;

public interface GenericGraph<N extends GenericNode<N,E,SO>, 
                              E extends GenericEdge<N,E,SO>,
                              SO extends SearchOptions> {

	GraphId id();
	String label();

	void write(GraphWriter writer);

	SO defaultSearchOptions();

	N findNode(String nodeId);
	N findNode(NodeId nodeId);

	Stream<N> startNodes();

	void forEachStartNode(NodeVisitor<N,E,SO> visitor);
	<R extends Object> Result<R> reduceStartNodes(R initialValue, NodeReducer<N,E,SO,R> reducer);

	Stream<N> nodes();

	void forEachNode(NodeVisitor<N,E,SO> visitor);
	<R extends Object> Result<R> reduceNodes(R initialValue, NodeReducer<N,E,SO,R> reducer);

	Stream<E> edges();

	void forEachEdge(EdgeVisitor<N,E,SO> visitor);
	<R extends Object> Result<R> reduceEdges(R initialValue, EdgeReducer<N,E,SO,R> reducer);

}
