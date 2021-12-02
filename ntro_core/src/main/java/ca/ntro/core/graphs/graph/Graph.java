package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.generic_graph.GenericGraph;

public interface Graph<N extends Node<N,E,SO>,
                       E extends Edge<N,E,SO>,
                       SO extends SearchOptions<SO>>

       extends    GenericGraph<N,E,SO> {
	

}
