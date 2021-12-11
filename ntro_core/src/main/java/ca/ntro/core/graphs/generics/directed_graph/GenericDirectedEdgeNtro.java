package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdgeNtro;

public class      GenericDirectedEdgeNtro<N extends GenericDirectedNode<N,E,SO>, 
                                          E extends GenericDirectedEdge<N,E,SO>,
                                          SO extends DirectedSearchOptions>

       extends    GenericEdgeNtro<N,E,SO>

       implements GenericDirectedEdge<N,E,SO> {

}
