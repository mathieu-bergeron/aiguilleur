package ca.ntro.jj.graphs.hierarchical_dag;

import ca.ntro.jj.graphs.dag.DagWriter;
import ca.ntro.jj.graphs.dag.Edge;
import ca.ntro.jj.graphs.dag.Node;

public interface HierarchicalDagWriter<C extends Cluster, N extends Node, E extends Edge> extends DagWriter<N,E> {
	
	void writeCluster(C cluster);

}
