package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagSearchOptions;

public interface HierarchicalDagWriter<NV extends NodeValue, EV extends EdgeValue> {
	
	void write(HierarchicalDag<HierarchicalDagSearchOptions,NV,EV> hierarchicalDag);

}
