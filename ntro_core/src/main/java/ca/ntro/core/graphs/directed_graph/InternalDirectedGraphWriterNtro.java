package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.graph.InternalGraphWriterNtro;
import ca.ntro.core.graphs.graph_writer.GraphWriterOptions;

public class      InternalDirectedGraphWriterNtro<N extends  DirectedNode<N,E>, 
                                                  E extends  DirectedEdge<N,E>>

       extends    InternalGraphWriterNtro<N,E,DirectedSearchOptions>

       implements InternalDirectedGraphWriter<N,E> {

	@Override
	protected GraphWriterOptions defaultOptions() {
		return GraphWriterOptions.directed(true);
	}
}
