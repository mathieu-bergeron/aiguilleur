package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.dag.Dag;
import ca.ntro.core.graphs.generic_graph.EdgeValue;
import ca.ntro.core.graphs.generic_graph.Node;
import ca.ntro.core.graphs.generic_graph.NodeReducer;
import ca.ntro.core.graphs.generic_graph.NodeValue;
import ca.ntro.core.graphs.generic_graph.NodeVisitor;
import ca.ntro.core.wrappers.result.Result;

public interface HierarchicalDag<NV extends NodeValue, EV extends EdgeValue> extends Dag<NV,EV> {

	void addNodeToCluster(Node<NV> cluster, Node<NV> node);

	void forEachNodeInCluster(Node<NV> cluster, NodeVisitor<Node<NV>> visitor);
	<R extends Object> Result<R> foldEachNodeInCluster(Node<NV> cluster, R initialValue, NodeReducer<Node<NV>, R> folder);

	void forEachNodeInClusterTransitive(Node<NV> cluster, NodeVisitor<Node<NV>> visitor);
	<R extends Object> Result<R> foldEachNodeInClusterTransitive(Node<NV> cluster, R initialValue, NodeReducer<Node<NV>, R> folder);

}
