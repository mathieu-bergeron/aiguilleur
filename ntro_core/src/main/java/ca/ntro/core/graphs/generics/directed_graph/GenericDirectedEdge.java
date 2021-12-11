package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;

public interface GenericDirectedEdge<N extends GenericDirectedNode<N,E,SO>, 
                                     E extends GenericDirectedEdge<N,E,SO>,
                                     SO extends DirectedGraphSearchOptions>

       extends   GenericEdge<N,E,SO> {

}
