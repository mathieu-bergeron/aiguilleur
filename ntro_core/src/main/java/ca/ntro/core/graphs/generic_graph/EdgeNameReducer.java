package ca.ntro.core.graphs.generic_graph;

public interface EdgeNameReducer<R extends Object> {
	
	R reduceEdgeName(R accumulator, String edgeName) throws Throwable;

}
