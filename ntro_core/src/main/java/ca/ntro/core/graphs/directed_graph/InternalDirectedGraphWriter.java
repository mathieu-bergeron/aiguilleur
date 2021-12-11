package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphWriterOptions;
import ca.ntro.core.graphs.generics.directed_graph.DirectedSearchOptions;
import ca.ntro.core.graphs.generics.graph.InternalGraphWriter;

public interface InternalDirectedGraphWriter<N extends  DirectedNode<N,E>, 
                                             E extends  DirectedEdge<N,E>>

       extends InternalGraphWriter<N,E,DirectedSearchOptions,DirectedGraphWriterOptions> {

}
