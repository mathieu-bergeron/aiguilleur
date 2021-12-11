package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedNode;

public interface DirectedNode<N extends  DirectedNode<N,E>, 
                              E extends  DirectedEdge<N,E>>

       extends GenericDirectedNode<N,
                                   E,
                                   DirectedGraphSearchOptions> {

}
