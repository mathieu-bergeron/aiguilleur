package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.graph.InternalGraphWriterNtro;

public class      InternalDirectedGraphWriterNtro<N extends  DirectedNode<N,E>, 
                                                  E extends  DirectedEdge<N,E>>

       extends    InternalGraphWriterNtro<N,E,DirectedSearchOptions,DirectedGraphWriterOptions>

       implements InternalDirectedGraphWriter<N,E> {

}
