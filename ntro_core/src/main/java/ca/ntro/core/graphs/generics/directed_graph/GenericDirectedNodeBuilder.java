package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericNodeBuilder;

public interface GenericDirectedNodeBuilder<N extends GenericDirectedNode<N,E,SO>,
                                            E extends GenericDirectedEdge<N,E,SO>,
                                            SO extends DirectedGraphSearchOptions,
									        NB extends GenericDirectedNodeBuilder<N,E,SO,NB>>

       extends GenericNodeBuilder<N,E,SO,NB> {

}
