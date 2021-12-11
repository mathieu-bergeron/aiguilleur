package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphWriterOptions;
import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilder;

public interface DirectedGraphBuilder<N extends  DirectedNode<N,E>, 
                                      E extends  DirectedEdge<N,E>>

       extends GenericDirectedGraphBuilder<N,
                                           E,
                                           DirectedGraphSearchOptions,
                                           DirectedNodeBuilder<N,E>,
                                           DirectedGraphWriterOptions,
                                           DirectedGraph<N,E>> {

}
