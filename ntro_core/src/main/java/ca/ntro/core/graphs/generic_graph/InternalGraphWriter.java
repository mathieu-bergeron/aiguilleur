package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.writers.GraphWriter;

public interface InternalGraphWriter<NV extends NodeValue, EV extends EdgeValue> {

	void write(GenericGraph<NV,EV> graph, GraphWriter writer);

}
