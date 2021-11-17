package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.dag.Dag;

public interface DagWriter<NV extends NodeValue, EV extends EdgeValue> {
	
	void write(Dag<NV,EV> dag);

}
