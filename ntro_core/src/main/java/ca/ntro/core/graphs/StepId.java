package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.GenericStep;
import ca.ntro.core.identifyers.Key;

public interface StepId extends GenericStep {

	Direction direction();
	Key name();

}
