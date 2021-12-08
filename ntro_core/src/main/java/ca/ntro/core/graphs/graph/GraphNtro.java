package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericGraphNtro;
import ca.ntro.core.graphs.generics.graph.InternalGraphWriter;
import ca.ntro.core.graphs.generics.graph.InternalGraphWriterNtro;

public class GraphNtro<N extends Node<N,E>,
                       E extends Edge<N,E>> 

       extends GenericGraphNtro<N,E,GraphSearchOptionsBuilder>

	   implements Graph<N,E> {
	
	@Override
	protected InternalGraphWriter<N, E, GraphSearchOptionsBuilder> newInternalGraphWriterInstance() {
		return new InternalGraphWriterNtro<>();
	}

	@Override
	protected GraphSearchOptionsBuilder newDefaultSearchOptionsInstance() {
		return new GraphSearchOptionsBuilderNtro();
	}
}
