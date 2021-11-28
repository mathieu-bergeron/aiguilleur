package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.StepId;

public interface StepIdReducer<R extends Object> {
	
	R reduceStep(R accumulator, StepId step) throws Throwable;

}
