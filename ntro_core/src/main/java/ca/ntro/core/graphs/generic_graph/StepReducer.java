package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Step;

public interface StepReducer<R extends Object> {
	
	R reduceStep(R accumulator, Step step) throws Throwable;

}
