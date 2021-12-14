package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generics.graph.GenericNodeFactory;

public interface DirectedNodeFactory<N extends  DirectedNode<N,E>, 
                                     E extends  DirectedEdge<N,E>>

       extends   GenericNodeFactory<N,E,DirectedGraphSearchOptions> {

}
