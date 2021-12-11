package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.graph.InternalGraphWriterNtro;

public class GenericInternalDirectedGraphWriterNtro<N extends GenericDirectedNode<N,E,SO>,
                                             E extends GenericDirectedEdge<N,E,SO>,
                                             SO extends DirectedGraphSearchOptions,
                                             GO extends DirectedGraphWriterOptions> 

       extends   InternalGraphWriterNtro<N,E,SO,GO> 

       implements GenericInternalDirectedGraphWriter<N,E,SO,GO> {

}
