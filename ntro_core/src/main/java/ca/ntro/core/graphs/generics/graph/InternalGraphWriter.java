package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.graph_writer.GraphWriter;

public interface InternalGraphWriter<N extends GenericNode<N,E,SO>,
                                     E extends GenericEdge<N,E,SO>,
                                     SO extends SearchOptions> {


	void write(GenericGraph<N,E,SO> graph, GraphWriter writer);

}
