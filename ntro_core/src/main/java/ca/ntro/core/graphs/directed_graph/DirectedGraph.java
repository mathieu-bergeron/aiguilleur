package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generics.generic_graph.Node;

public interface DirectedGraph<N extends Node<N,E,SO>,
                               E extends Edge<N,E,SO>,
                               SO extends DirectedGraphSearchOptionsBuilder>

       extends   GenericGraph<N,E,SO> {

}
