package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.dag.Dag;
import ca.ntro.core.graphs.generic_graph.Edge;
import ca.ntro.core.graphs.generic_graph.NodeReducer;
import ca.ntro.core.graphs.generic_graph.NodeVisitor;
import ca.ntro.core.wrappers.result.Result;

public interface HierarchicalDag<HN extends HierarchicalNode, E extends Edge> extends Dag<HN,E> {

	void addNodeToCluster(HN cluster, HN node);

	void forEachNodeInCluster(HN cluster, NodeVisitor<HN> visitor);
	<R extends Object> Result<R> foldEachNodeInCluster(HN cluster, R initialValue, NodeReducer<HN, R> folder);

	void forEachNodeInClusterTransitive(HN cluster, NodeVisitor<HN> visitor);
	<R extends Object> Result<R> foldEachNodeInClusterTransitive(HN cluster, R initialValue, NodeReducer<HN, R> folder);

}
