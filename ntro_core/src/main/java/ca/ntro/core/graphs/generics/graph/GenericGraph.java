package ca.ntro.core.graphs.generics.graph;


import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.stream.Stream;

public interface GenericGraph<N extends GenericNode<N,E,SO>, 
                              E extends GenericEdge<N,E,SO>,
                              SO extends SearchOptions,
                              GO extends GraphWriterOptions> {

	GraphId id();
	String label();

	void write(GraphWriter writer);
	void write(GO options, GraphWriter writer);

	SO defaultSearchOptions();
	GO defaultGraphWriterOptions();

	N findNode(String nodeId);
	N findNode(NodeId nodeId);

	Stream<N> startNodes();
	Stream<N> nodes();
	Stream<E> edges();

}
