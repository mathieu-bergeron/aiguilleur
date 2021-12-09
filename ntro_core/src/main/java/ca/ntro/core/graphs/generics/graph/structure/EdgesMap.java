package ca.ntro.core.graphs.generics.graph.structure;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.generics.graph.EdgeReducer;
import ca.ntro.core.graphs.generics.graph.EdgeTypeReducer;
import ca.ntro.core.graphs.generics.graph.GenericNode;
import ca.ntro.core.graphs.generics.graph.SearchOptions;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface EdgesMap<N extends GenericNode<N,E,SO>, 
                          E extends GenericEdge<N,E,SO>,
                          SO extends SearchOptions> {
	
	boolean containsEdge(E edge);

	void addEdge(E edge);

	<R> void _reduceEdgeTypesForDirection(Direction direction, ResultNtro<R> result, EdgeTypeReducer<R> reducer);
	<R> void _reduceEdgesByType(EdgeType edgeName, ResultNtro<R> result, EdgeReducer<N,E,SO,R> reducer);

}
