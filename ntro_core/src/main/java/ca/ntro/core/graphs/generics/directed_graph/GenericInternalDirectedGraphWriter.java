package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericInternalGraphWriter;

public interface GenericInternalDirectedGraphWriter<N extends GenericDirectedNode<N,E,SO>,
                                             E extends GenericDirectedEdge<N,E,SO>,
                                             SO extends DirectedGraphSearchOptions,
                                             GO extends DirectedGraphWriterOptions> 

       extends   GenericInternalGraphWriter<N,E,SO,GO> {

}
