package ca.ntro.core.graphs.dag;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.directed_graph.DirectedGraph;

public interface Dag<N extends Node<N,E,SO>,
                     E extends Edge<N,E,SO>,
                     SO extends SearchOptions<SO>>

       extends   DirectedGraph<N,E,SO> {

}
