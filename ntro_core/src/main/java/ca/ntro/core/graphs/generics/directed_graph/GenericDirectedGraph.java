package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericGraph;

public interface GenericDirectedGraph<N extends  GenericDirectedNode<N,E,SO>, 
                                      E extends  GenericDirectedEdge<N,E,SO>,
                                      SO extends DirectedSearchOptions> 

       extends   GenericGraph<N,E,SO> {

}
