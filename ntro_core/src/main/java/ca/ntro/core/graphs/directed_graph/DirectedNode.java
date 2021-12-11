package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedSearchOptions;
import ca.ntro.core.graphs.generics.graph.GenericNode;

public interface DirectedNode<N extends  DirectedNode<N,E>, 
                              E extends  DirectedEdge<N,E>>

       extends GenericNode<N,E,DirectedSearchOptions> {

}
