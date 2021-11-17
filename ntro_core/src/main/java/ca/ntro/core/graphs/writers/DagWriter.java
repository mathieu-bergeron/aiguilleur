package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.dag.Dag;
import ca.ntro.core.graphs.generic_graph.EdgeValue;
import ca.ntro.core.graphs.generic_graph.NodeValue;

public interface DagWriter<NV extends NodeValue, EV extends EdgeValue> {
	
	void write(Dag<NV,EV> dag);

}
