package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generic_graph.GenericNodeBuilder;

public interface DirectedGraphBuilder<N extends Node<N,E,SO>,
                                      E extends Edge<N,E,SO>,
                                      SO extends DirectedGraphSearchOptionsBuilder,
                                      NB extends GenericNodeBuilder<N,E,SO,NB>>

       extends   GenericGraphBuilder<N,E,SO,NB,DirectedGraph<N,E,SO>> {

}
