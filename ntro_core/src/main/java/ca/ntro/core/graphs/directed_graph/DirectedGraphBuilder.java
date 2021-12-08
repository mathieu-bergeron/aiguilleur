package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generics.graph.GenericNodeBuilder;
import ca.ntro.core.graphs.generics.graph.GenericNode;

public interface DirectedGraphBuilder<N extends GenericNode<N,E,SO>,
                                      E extends GenericEdge<N,E,SO>,
                                      SO extends DirectedGraphSearchOptionsBuilder,
                                      NB extends GenericNodeBuilder<N,E,SO,NB>>

       extends   GenericGraphBuilder<N,E,SO,NB,DirectedGraph<N,E,SO>> {

}
