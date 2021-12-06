package ca.ntro.core.graphs.generics.directed_graph;

public interface Edge<N extends GenericNode<N,E,SO>, 
                      E extends Edge<N,E,SO>,
                      SO extends SearchOptionsBuilder> extends GenericEdge {

	N from();
	N to();

	EdgeType type();
	EdgeId id();

}
