package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generics.generic_graph.GenericNodeBuilder;
import ca.ntro.core.graphs.generics.generic_graph.Node;

public interface DirectedGraphBuilder<N extends Node<N,E,SO>,
                                      E extends Edge<N,E,SO>,
                                      SO extends DirectedGraphSearchOptionsBuilder,
                                      NB extends GenericNodeBuilder<N,E,SO,NB>>

       extends   GenericGraphBuilder<N,E,SO,NB,DirectedGraph<N,E,SO>> {

}
