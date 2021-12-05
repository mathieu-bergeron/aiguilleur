package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.Walk;

public interface ReachedNode <N extends Node<N,E,SO>, 
                              E extends Edge<N,E,SO>,
                              SO extends SearchOptionsBuilder> {
	
	Walk<N,E,SO> walked();
	N node();

}