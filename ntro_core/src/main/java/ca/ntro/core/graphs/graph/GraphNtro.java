package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericGraphNtro;
import ca.ntro.core.graphs.generics.graph.GraphWriterOptions;
import ca.ntro.core.graphs.generics.graph.GraphWriterOptionsNtro;
import ca.ntro.core.graphs.generics.graph.InternalGraphWriter;
import ca.ntro.core.graphs.generics.graph.InternalGraphWriterNtro;

public class GraphNtro<N extends Node<N,E>,
                       E extends Edge<N,E>> 

       extends GenericGraphNtro<N,E,GraphSearchOptions,GraphWriterOptions>

	   implements Graph<N,E> {
	
	@Override
	protected InternalGraphWriter<N, E, GraphSearchOptions,GraphWriterOptions> newInternalGraphWriterInstance() {
		return new InternalGraphWriterNtro<>();
	}

	@Override
	protected GraphSearchOptions newDefaultSearchOptionsInstance() {
		return new GraphSearchOptionsNtro();
	}

	@Override
	protected GraphWriterOptions newDefaultGraphWriterOptionsInstance() {
		return new GraphWriterOptionsNtro();
	}
}
