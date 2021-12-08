package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;

public interface DirectedEdge<N extends  DirectedNode<N,E>, 
                              E extends  DirectedEdge<N,E>>

       extends GenericEdge<N,E,DirectedSearchOptions> {

}
