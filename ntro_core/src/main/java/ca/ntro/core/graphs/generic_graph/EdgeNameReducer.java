package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.EdgeName;

public interface EdgeNameReducer<R extends Object> {

	R reduceStep(R accumulator, EdgeName edgeName) throws Throwable;

}
