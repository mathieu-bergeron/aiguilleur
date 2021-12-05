package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generic_graph.WalkId;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.wrappers.result.Result;

public interface Node<N extends Node<N,E,SO>, 
                      E extends Edge<N,E,SO>,
                      SO extends SearchOptionsBuilder> {

	NodeId id();
	GenericGraph<N,E,SO> parentGraph();

	boolean isStartNode();
	boolean isPartOfCycle();

	void forEachEdge(EdgeVisitor<N,E,SO> visitor);
	<R extends Object> Result<R> reduceEdges(R initialValue, EdgeReducer<N,E,SO,R> reducer);

	void forEachReachableNode(ReachableNodeVisitor<N,E,SO> visitor);
	void forEachReachableNode(SO options, ReachableNodeVisitor<N,E,SO> visitor);

	Stream<ReachedNode<N,E,SO>> reachableNodes();
	Stream<ReachedNode<N,E,SO>> reachableNodes(SO options);

	<R extends Object> Result<R> reduceReachableNodes(R initialValue, ReachableNodeReducer<N,E,SO,R> reducer);
	<R extends Object> Result<R> reduceReachableNodes(SO options, R initialValue, ReachableNodeReducer<N,E,SO,R> reducer);

	//Stream<N> reachableEdges();
	//Stream<N> reachableEdges(SO options);

	void forEachReachableEdge(ReachableEdgeVisitor<N,E,SO> visitor);
	void forEachReachableEdge(SO options, ReachableEdgeVisitor<N,E,SO> visitor);

	<R extends Object> Result<R> reduceReachableEdges(R initialValue, ReachableEdgeReducer<N,E,SO,R> reducer);
	<R extends Object> Result<R> reduceReachableEdges(SO options, R initialValue, ReachableEdgeReducer<N,E,SO,R> reducer);

	void visitWalk(WalkId walk, WalkVisitor<N,E,SO> visitor);
	<R extends Object> Result<R> reduceWalk(WalkId walk, R initialValue, WalkReducer<N,E,SO,R> reducer);

}
