package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilder;
import ca.ntro.core.graphs.generics.directed_graph.GenericNode;
import ca.ntro.core.graphs.generics.directed_graph.GenericNodeBuilder;

public interface DirectedGraphBuilder<N extends GenericNode<N,E,SO>,
                                      E extends Edge<N,E,SO>,
                                      SO extends DirectedGraphSearchOptionsBuilder,
                                      NB extends GenericNodeBuilder<N,E,SO,NB>>

       extends   GenericDirectedGraphBuilder<N,E,SO,NB,DirectedGraph<N,E,SO>> {

}
