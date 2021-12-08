package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.common.EdgeId;
import ca.ntro.core.graphs.common.EdgeType;

public interface GenericEdge<N extends GenericNode<N,E,SO>, 
                      E extends GenericEdge<N,E,SO>,
                      SO extends SearchOptions> extends GenericStep {

	N from();
	N to();

	EdgeType type();
	EdgeId id();

	boolean equalsUndirected(E edge);

}
