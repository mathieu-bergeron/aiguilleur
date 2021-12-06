package ca.ntro.core.graphs.generics.generic_hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.Node;
import ca.ntro.core.graphs.generics.generic_graph.ReachableNodeReducer;
import ca.ntro.core.graphs.generics.generic_graph.ReachableNodeVisitor;
import ca.ntro.core.wrappers.result.Result;

public interface GenericHierarchicalNode<N extends GenericHierarchicalNode<N,E,SO>,
                                  E extends Edge<N,E,SO>,
                                  SO extends HierarchicalGraphSearchOptionsBuilder>

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
