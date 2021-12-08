package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericNodeBuilder;

public interface DirectedNodeBuilder<N extends  DirectedNode<N,E>, 
                                     E extends  DirectedEdge<N,E>>

       extends GenericNodeBuilder<N,E,DirectedSearchOptions,DirectedNodeBuilder<N,E>> {

}
