package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedNodeBuilder;

public interface DirectedNodeBuilder<N extends  DirectedNode<N,E>, 
                                     E extends  DirectedEdge<N,E>>

       extends GenericDirectedNodeBuilder<N,
                                          E,
                                          DirectedGraphSearchOptions,
                                          DirectedNodeBuilder<N,E>> {

}
