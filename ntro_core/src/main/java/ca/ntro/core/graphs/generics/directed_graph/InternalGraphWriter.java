package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.writers.GraphWriter;

public interface InternalGraphWriter<N extends GenericNode<N,E,SO>,
                                     E extends Edge<N,E,SO>,
                                     SO extends SearchOptionsBuilder> {


	void write(GenericDirectedGraph<N,E,SO> graph, GraphWriter writer);

}
