package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.GenericEdge;

public interface Edge<N extends Node<N,E,SO>, 
                      E extends Edge<N,E,SO>,
                      SO extends SearchOptionsBuilder> extends GenericEdge {

	N from();
	N to();

	EdgeType type();
	EdgeId id();

}
