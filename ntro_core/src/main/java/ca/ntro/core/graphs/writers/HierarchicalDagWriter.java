package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.generic_graph.Edge;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalNode;

public interface HierarchicalDagWriter<HN extends HierarchicalNode, E extends Edge> {
	
	void write(HierarchicalDag<HN,E> hierarchicalDag);

}
