package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.dag.Dag;
import ca.ntro.core.graphs.dag.Edge;
import ca.ntro.core.graphs.dag.exceptions.NodeNotFoundException;

public interface HierarchicalDag<HN extends HierarchicalNode, E extends Edge> extends Dag<HN,E> {

	void addNodeToCluster(HN cluster, HN node);
	void addNodeToCluster(HierarchicalNodeId clusterId, HN node) throws NodeNotFoundException;

	void write(HierarchicalDagWriter<HN,E> writer);

}
