package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericGraphNtro;
import ca.ntro.core.graphs.generics.graph.InternalGraphWriter;

public class      DirectedGraphNtro<N extends  DirectedNode<N,E>, 
                                    E extends  DirectedEdge<N,E>>

       extends    GenericGraphNtro<N,E,DirectedSearchOptions,DirectedGraphWriterOptions>


       implements DirectedGraph<N,E> {

	@Override
	protected InternalGraphWriter<N,E,DirectedSearchOptions,DirectedGraphWriterOptions> newInternalGraphWriterInstance() {
		return new InternalDirectedGraphWriterNtro<N,E>();
	}

	@Override
	protected DirectedSearchOptions newDefaultSearchOptionsInstance() {
		return new DirectedSearchOptionsNtro();
	}

	@Override
	protected DirectedGraphWriterOptions newDefaultGraphWriterOptionsInstance() {
		return new DirectedGraphWriterOptionsNtro();
	}

}
