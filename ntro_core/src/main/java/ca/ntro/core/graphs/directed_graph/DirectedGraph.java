package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.generic_graph.GenericGraph;

public interface DirectedGraph<N extends Node<N,E,SO>,
                               E extends Edge<N,E,SO>,
                               SO extends DirectedGraphSearchOptionsBuilder>

       extends   GenericGraph<N,E,SO> {

}
