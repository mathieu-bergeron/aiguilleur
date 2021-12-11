package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericGraphBuilder;

public interface GenericDirectedGraphBuilder<N extends  GenericDirectedNode<N,E,SO>,
                                             E extends  GenericDirectedEdge<N,E,SO>,
                                             SO extends DirectedGraphSearchOptions,
									         NB extends GenericDirectedNodeBuilder<N,E,SO,NB>,
									         GO extends DirectedGraphWriterOptions,
                                             G extends  GenericDirectedGraph<N,E,SO,GO>> 

       extends   GenericGraphBuilder<N,E,SO,NB,GO,G> {

}
