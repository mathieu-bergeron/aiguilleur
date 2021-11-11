package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.dag.DagWriter;
import ca.ntro.core.graphs.dag.Edge;

public interface HierarchicalDagWriter<HN extends HierarchicalNode, E extends Edge> extends DagWriter<HN,E> {
	
	void writeCluster(HN cluster);

}
