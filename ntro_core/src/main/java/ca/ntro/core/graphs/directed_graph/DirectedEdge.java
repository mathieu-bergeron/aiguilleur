package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedSearchOptions;
import ca.ntro.core.graphs.generics.graph.GenericEdge;

public interface DirectedEdge<N extends  DirectedNode<N,E>, 
                              E extends  DirectedEdge<N,E>>

       extends GenericEdge<N,E,DirectedSearchOptions> {

}
