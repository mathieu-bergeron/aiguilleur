package ca.ntro.core.graphs.dag;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.directed_graph.DirectedGraph;

public interface Dag<N extends Node<N,E,SO>,
                     E extends Edge<N,E,SO>,
                     SO extends DagSearchOptionsBuilder>

       extends   DirectedGraph<N,E,SO> {

}
