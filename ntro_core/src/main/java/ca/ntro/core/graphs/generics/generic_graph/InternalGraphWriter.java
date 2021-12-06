package ca.ntro.core.graphs.generics.generic_graph;

import ca.ntro.core.graphs.writers.GraphWriter;

public interface InternalGraphWriter<N extends Node<N,E,SO>,
                                     E extends Edge<N,E,SO>,
                                     SO extends SearchOptionsBuilder> {


	void write(GenericGraph<N,E,SO> graph, GraphWriter writer);

}
