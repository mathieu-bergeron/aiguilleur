package ca.ntro.jj.graphs.hierarchical_dag;

import ca.ntro.jj.graphs.dag.Dag;
import ca.ntro.jj.graphs.dag.Edge;
import ca.ntro.jj.graphs.dag.Node;

public interface HierarchicalDag<C extends Cluster, N extends Node, E extends Edge> extends Dag<N,E> {
	
	void addNodeToCluster(C cluster, N node);
	void addNodeToCluster(ClusterId clusterId, N node);
	
	void write(HierarchicalDagWriter<C,N,E> writer);

}
