package ca.ntro.jj.graphs.hierarchical_dag;

import ca.ntro.jj.graphs.dag.DagWriter;
import ca.ntro.jj.graphs.dag.Edge;

public interface HierarchicalDagWriter<HN extends HierarchicalNode, E extends Edge> extends DagWriter<HN,E> {
	
	void writeCluster(HN cluster);

}
