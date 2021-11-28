package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.WalkId;
import ca.ntro.core.wrappers.result.Result;

public interface Node<N extends Node<N,E,SO>, 
                      E extends Edge<N,E,SO>,
                      SO extends SearchOptions> {

	NodeId id();

	void forEachEdge(EdgeVisitor<N,E> visitor);
	<R extends Object> Result<R> reduceEdges(R initialValue, EdgeReducer<N,E,SO,R> reducer);

	void forEachReachableNode(ReachableNodeVisitor<N,E> visitor);
	void forEachReachableNode(SO options, ReachableNodeVisitor<N,E> visitor);

	<R extends Object> Result<R> reduceReachableNodes(R initialValue, ReachableNodeReducer<N,E,R> reducer);
	<R extends Object> Result<R> reduceReachableNodes(SO options, R initialValue, ReachableNodeReducer<N,E,R> reducer);

	void forEachReachableEdge(ReachableStepVisitor<N,E> visitor);
	void forEachReachableEdge(SO options, ReachableStepVisitor<N,E> visitor);

	<R extends Object> Result<R> reduceReachableEdges(R initialValue, ReachableStepReducer<N,E,R> reducer);
	<R extends Object> Result<R> reduceReachableEdges(SO options, R initialValue, ReachableStepReducer<N,E,R> reducer);

	void visitWalk(WalkId walk, WalkVisitor<N,E,SO> visitor);
	<R extends Object> Result<R> reduceWalk(WalkId walk, R initialValue, WalkReducer<N,E,SO,R> reducer);

}
