package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphWriterOptions;
import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphWriterOptionsNtro;
import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphSearchOptionsNtro;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphNtro;
import ca.ntro.core.graphs.generics.graph.GenericInternalGraphWriter;

public class      DirectedGraphNtro<N extends  DirectedNode<N,E>, 
                                    E extends  DirectedEdge<N,E>>

       extends    GenericDirectedGraphNtro<N,
                                           E,
                                           DirectedGraphSearchOptions,
                                           DirectedGraphWriterOptions>


       implements DirectedGraph<N,E> {

	@Override
	protected GenericInternalGraphWriter<N,E,DirectedGraphSearchOptions,DirectedGraphWriterOptions> newInternalGraphWriterInstance() {
		return new InternalDirectedGraphWriterNtro<N,E>();
	}

	@Override
	protected DirectedGraphSearchOptions newDefaultSearchOptionsInstance() {
		return new DirectedGraphSearchOptionsNtro();
	}

	@Override
	protected DirectedGraphWriterOptions newDefaultGraphWriterOptionsInstance() {
		return new DirectedGraphWriterOptionsNtro();
	}

}
