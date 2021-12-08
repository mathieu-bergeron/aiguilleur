package ca.ntro.core.graphs.generics.generic_graph;

public interface Edge<N extends Node<N,E,SO>, 
                      E extends Edge<N,E,SO>,
                      SO extends SearchOptionsBuilder> extends GenericStep {

	N from();
	N to();

	EdgeType type();
	EdgeId id();

	boolean equalsUndirected(E edge);

}
