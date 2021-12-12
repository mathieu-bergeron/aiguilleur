package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.stream.Stream;

public interface GenericNodeStructure<N extends GenericNode<N,E,SO>, 
                                      E extends GenericEdge<N,E,SO>,
                                      SO extends SearchOptions> {
	
	boolean isStartNode();

	Stream<EdgeType> edgeTypes(Direction direction);
	Stream<E> edges(EdgeType edgeType);

	N node();

}
