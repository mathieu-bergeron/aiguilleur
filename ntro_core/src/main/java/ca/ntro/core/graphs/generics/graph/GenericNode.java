package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.wrappers.result.Result;


public interface GenericNode<N extends GenericNode<N,E,SO>, 
                             E extends GenericEdge<N,E,SO>,
                             SO extends SearchOptions> {

	NodeId id();
	String label();

	GenericGraph<N,E,SO,?> parentGraph();

	boolean isStartNode();
	boolean isPartOfCycle();
	
	Stream<E> edges();
	Stream<E> edges(SO options);

	void forEachEdge(EdgeVisitor<N,E,SO> visitor);
	<R extends Object> Result<R> reduceEdges(R initialValue, EdgeReducer<N,E,SO,R> reducer);

	Stream<VisitedNode<N,E,SO>> reachableNodes();
	Stream<VisitedNode<N,E,SO>> reachableNodes(SO options);

	void forEachReachableNode(ReachableNodeVisitor<N,E,SO> visitor);
	void forEachReachableNode(SO options, ReachableNodeVisitor<N,E,SO> visitor);

	<R extends Object> Result<R> reduceReachableNodes(R initialValue, ReachableNodeReducer<N,E,SO,R> reducer);
	<R extends Object> Result<R> reduceReachableNodes(SO options, R initialValue, ReachableNodeReducer<N,E,SO,R> reducer);

	Stream<VisitedEdge<N,E,SO>> reachableEdges();
	Stream<VisitedEdge<N,E,SO>> reachableEdges(SO options);

	void forEachReachableEdge(ReachableEdgeVisitor<N,E,SO> visitor);
	void forEachReachableEdge(SO options, ReachableEdgeVisitor<N,E,SO> visitor);

	<R extends Object> Result<R> reduceReachableEdges(R initialValue, ReachableEdgeReducer<N,E,SO,R> reducer);
	<R extends Object> Result<R> reduceReachableEdges(SO options, R initialValue, ReachableEdgeReducer<N,E,SO,R> reducer);
	
	Stream<WalkInProgress<N,E,SO>> walk();
	Stream<WalkInProgress<N,E,SO>> walk(SO options);

	void visitWalk(WalkId walk, WalkVisitor<N,E,SO> visitor);
	<R extends Object> Result<R> reduceWalk(WalkId walk, R initialValue, WalkReducer<N,E,SO,R> reducer);

}
