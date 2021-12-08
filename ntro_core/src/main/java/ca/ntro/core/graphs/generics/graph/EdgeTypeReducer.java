package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.common.EdgeType;

public interface EdgeTypeReducer<R extends Object> {

	R reduceEdgeType(R accumulator, EdgeType edgeType) throws Throwable;

}
