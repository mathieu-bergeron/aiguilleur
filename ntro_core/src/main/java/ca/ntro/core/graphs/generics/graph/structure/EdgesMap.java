package ca.ntro.core.graphs.generics.graph.structure;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.generics.graph.GenericNode;
import ca.ntro.core.graphs.generics.graph.SearchOptions;
import ca.ntro.core.stream.Stream;

public interface EdgesMap<N extends GenericNode<N,E,SO>, 
                          E extends GenericEdge<N,E,SO>,
                          SO extends SearchOptions> {
	
	boolean containsEdge(E edge);

	void addEdge(E edge);

	Stream<EdgeType> edgeTypes(Direction direction);
	Stream<E> edges(EdgeType edgeType);

}
