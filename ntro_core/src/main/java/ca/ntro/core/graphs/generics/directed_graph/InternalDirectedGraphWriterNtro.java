package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.graph.InternalGraphWriterNtro;
import ca.ntro.core.graphs.graph_writer.GraphWriterOptions;

public class      InternalDirectedGraphWriterNtro<N extends  GenericDirectedNode<N,E,SO>, 
                                                  E extends  GenericDirectedEdge<N,E,SO>,
                                                  SO extends DirectedSearchOptions> 

       extends    InternalGraphWriterNtro<N,E,SO>

       implements InternalDirectedGraphWriter<N,E,SO> {

	@Override
	protected GraphWriterOptions defaultOptions() {
		return GraphWriterOptions.directed(true);
	}
}
