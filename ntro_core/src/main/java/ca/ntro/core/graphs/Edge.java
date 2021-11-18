package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.EdgeNameReducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface Edge<EV extends EdgeValue> {
	
	EdgeId id();

	EV value();

}
