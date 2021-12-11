package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericGraphBuilder;

public interface DirectedGraphBuilder<N extends  DirectedNode<N,E>, 
                                      E extends  DirectedEdge<N,E>>

       extends GenericGraphBuilder<N,
                                   E,
                                   DirectedSearchOptions,
                                   DirectedNodeBuilder<N,E>,
                                   DirectedGraphWriterOptions,
                                   DirectedGraph<N,E>> {

}
