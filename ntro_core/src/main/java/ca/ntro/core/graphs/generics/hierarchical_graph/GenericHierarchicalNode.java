package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericNode;
import ca.ntro.core.graphs.generics.graph.ReachableNodeReducer;
import ca.ntro.core.graphs.generics.graph.ReachableNodeVisitor;
import ca.ntro.core.graphs.generics.graph.VisitedNode;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.wrappers.result.Result;

public interface GenericHierarchicalNode<N extends GenericHierarchicalNode<N,E,SO>,
                                         E extends GenericEdge<N,E,SO>,
                                         SO extends HierarchicalSearchOptions>

       extends   GenericNode<N,E,SO> {
	
	boolean hasSubNodes();
	boolean hasParent();

	N parent();
	
	Stream<VisitedNode<N,E,SO>> subNodes();
	Stream<VisitedNode<N,E,SO>> subNodes(SO options);

	//void forEachSubNode(ReachableNodeVisitor<N,E,SO> visitor);
	//void forEachSubNode(SO options, ReachableNodeVisitor<N,E,SO> visitor);

	//<R extends Object> Result<R> reduceSubNodes(R initialValue, ReachableNodeReducer<N,E,SO,R> reducer);
	//<R extends Object> Result<R> reduceSubNodes(SO options, R initialValue, ReachableNodeReducer<N,E,SO,R> reducer);

	Stream<VisitedNode<N,E,SO>> parentNodes();
	Stream<VisitedNode<N,E,SO>> parentNodes(SO options);

	//void forEachParentNode(ReachableNodeVisitor<N,E,SO> visitor);
	//void forEachParentNode(SO options, ReachableNodeVisitor<N,E,SO> visitor);

	//<R extends Object> Result<R> reduceParentNodes(R initialValue, ReachableNodeReducer<N,E,SO,R> reducer);
	//<R extends Object> Result<R> reduceParentNodes(SO options, R initialValue, ReachableNodeReducer<N,E,SO,R> reducer);


}
