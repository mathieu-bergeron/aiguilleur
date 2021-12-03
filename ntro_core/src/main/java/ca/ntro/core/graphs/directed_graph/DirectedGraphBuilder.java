package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;

public interface DirectedGraphBuilder<N extends Node<N,E,SO>,
                                      E extends Edge<N,E,SO>,
                                      SO extends DirectedGraphSearchOptionsBuilder>

       extends   GenericGraphBuilder<N,E,SO,DirectedGraph<N,E,SO>> {

}
