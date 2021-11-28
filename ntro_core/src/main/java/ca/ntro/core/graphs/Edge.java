package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.GenericEdge;

public interface Edge<N extends Node<N,E,SO>, 
                      E extends Edge<N,E,SO>,
                      SO extends SearchOptions> extends GenericEdge {

	N from();

	EdgeName id();

	N to();

}
