package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.graph.InternalGraphWriter;

public interface InternalDirectedGraphWriter<N extends  GenericDirectedNode<N,E,SO>, 
                                             E extends  GenericDirectedEdge<N,E,SO>,
                                             SO extends DirectedSearchOptions> 

       extends InternalGraphWriter<N,E,SO> {

}
