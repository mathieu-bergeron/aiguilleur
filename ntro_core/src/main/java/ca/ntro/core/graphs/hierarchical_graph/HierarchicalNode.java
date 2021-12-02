package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.ReachableNodeReducer;
import ca.ntro.core.graphs.ReachableNodeVisitor;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.wrappers.result.Result;

public interface HierarchicalNode<N extends HierarchicalNode<N,E,SO>,
                                  E extends Edge<N,E,SO>,
                                  SO extends SearchOptionsNtro>

       extends   Node<N,E,SO> {
	
	boolean hasSubNodes();
	boolean hasParent();

	N parent();

	void forEachSubNode(ReachableNodeVisitor<N,E,SO> visitor);
	void forEachSubNode(SO options, ReachableNodeVisitor<N,E,SO> visitor);

	<R extends Object> Result<R> reduceSubNodes(R initialValue, ReachableNodeReducer<N,E,SO,R> reducer);
	<R extends Object> Result<R> reduceSubNodes(SO options, R initialValue, ReachableNodeReducer<N,E,SO,R> reducer);

	void forEachParentNode(ReachableNodeVisitor<N,E,SO> visitor);
	void forEachParentNode(SO options, ReachableNodeVisitor<N,E,SO> visitor);

	<R extends Object> Result<R> reduceParentNodes(R initialValue, ReachableNodeReducer<N,E,SO,R> reducer);
	<R extends Object> Result<R> reduceParentNodes(SO options, R initialValue, ReachableNodeReducer<N,E,SO,R> reducer);


}
