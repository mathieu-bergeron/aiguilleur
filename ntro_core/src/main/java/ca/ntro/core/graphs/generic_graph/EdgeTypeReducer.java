package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.EdgeType;

public interface EdgeTypeReducer<R extends Object> {

	R reduceEdgeType(R accumulator, EdgeType edgeType) throws Throwable;

}
