package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.NodeVisitor;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.dag.Dag;
import ca.ntro.core.wrappers.result.Result;

public interface HierarchicalDag<SO extends SearchOptions, NV extends NodeValue, EV extends EdgeValue> extends Dag<NV,EV> {

	void addSubNode(Node<NV> parentNode, Node<NV> subNode);

	void forEachSubNode(Node<NV> parentNode, NodeVisitor<NV> visitor);
	void forEachSubNode(Node<NV> parentNode, SO options, NodeVisitor<NV> visitor);
	
	<R extends Object> Result<R> reduceSubNodes(Node<NV> parentNode, R initialValue, NodeReducer<NV,R> reducer);
	<R extends Object> Result<R> reduceSubNodes(Node<NV> parentNode, SO options, R initialValue, NodeReducer<NV,R> reducer);


}
