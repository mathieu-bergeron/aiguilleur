package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptionsBuilder;
import ca.ntro.core.graphs.generic_graph.GenericGraph;

public interface Graph<N extends Node<N,E,SO>,
                       E extends Edge<N,E,SO>,
                       SO extends SearchOptionsBuilder>

       extends    GenericGraph<N,E,SO> {

	

}
