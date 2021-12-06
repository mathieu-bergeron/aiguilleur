package ca.ntro.core.graphs.generics.directed_graph;

public interface EdgeTypeReducer<R extends Object> {

	R reduceEdgeType(R accumulator, EdgeType edgeType) throws Throwable;

}