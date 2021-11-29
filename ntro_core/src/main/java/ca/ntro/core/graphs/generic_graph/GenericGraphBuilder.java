package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptions;

public interface GenericGraphBuilder<N extends Node<N,E,SO>,
                                     E extends Edge<N,E,SO>,
                                     SO extends SearchOptions,
                                     G extends GenericGraph<N,E,SO>> {

	void addNode(N node);
	
	G toGraph();
}
