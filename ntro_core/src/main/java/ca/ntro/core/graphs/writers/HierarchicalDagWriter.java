package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.generic_graph.EdgeValue;
import ca.ntro.core.graphs.generic_graph.NodeValue;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;

public interface HierarchicalDagWriter<NV extends NodeValue, EV extends EdgeValue> {
	
	void write(HierarchicalDag<NV,EV> hierarchicalDag);

}
