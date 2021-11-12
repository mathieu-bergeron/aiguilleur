package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.dag.Dag;
import ca.ntro.core.graphs.dag.Edge;
import ca.ntro.core.graphs.dag.NodeFolder;
import ca.ntro.core.graphs.dag.NodeVisitor;
import ca.ntro.core.wrappers.Result;

public interface HierarchicalDag<HN extends HierarchicalNode, E extends Edge> extends Dag<HN,E> {

	void addNodeToCluster(HN cluster, HN node);

	void forEachNodeInCluster(HN cluster, NodeVisitor<HN> visitor);
	<R extends Object> Result<R> foldEachNodeInCluster(HN cluster, R initialValue, NodeFolder<HN, R> folder);

	void forEachNodeInClusterTransitive(HN cluster, NodeVisitor<HN> visitor);
	<R extends Object> Result<R> foldEachNodeInClusterTransitive(HN cluster, R initialValue, NodeFolder<HN, R> folder);

	void write(HierarchicalDagWriter<HN,E> writer);

}
