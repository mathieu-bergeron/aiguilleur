package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.generic_graph.GenericGraphNtro;
import ca.ntro.core.graphs.generics.generic_graph.InternalGraphWriter;
import ca.ntro.core.graphs.generics.generic_graph.InternalGraphWriterNtro;

public class GraphNtro<N extends GraphNode<N,E>,
                       E extends GraphEdge<N,E>> 

       extends GenericGraphNtro<N,E,GraphSearchOptionsBuilder>

	   implements Graph<N,E> {
	
	@Override
	protected InternalGraphWriter<N, E, GraphSearchOptionsBuilder> createInternalGraphWriter() {
		return new InternalGraphWriterNtro<>();
	}

	@Override
	protected GraphSearchOptionsBuilder createDefaultSearchOptions() {
		return new GraphSearchOptionsBuilderNtro();
	}
}
