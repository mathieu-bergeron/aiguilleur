package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericGraphNtro;

public abstract class GenericDirectedGraphNtro<N extends GenericDirectedNode<N,E,SO>, 
                                               E extends GenericDirectedEdge<N,E,SO>,
                                               SO extends DirectedGraphSearchOptions,
                                               GO extends DirectedGraphWriterOptions> 

       extends        GenericGraphNtro<N,E,SO,GO> 

       implements     GenericDirectedGraph<N,E,SO,GO> {

}
