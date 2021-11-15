package ca.ntro.core.graphs.hierarchical_dag;

import java.util.List;

import ca.ntro.core.graphs.dag.Edge;
import ca.ntro.core.graphs.writers.DagWriter;

public interface HierarchicalDagWriter<HN extends HierarchicalNode, E extends Edge> extends DagWriter<HN,E> {
	
	void writeCluster(HN cluster, List<HN> children);

}
