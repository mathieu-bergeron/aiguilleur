package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.wrappers.result.Result;

public interface GenericGraph<SO extends SearchOptions, N extends Node, E extends Edge> {

	GraphId id();
	String label();

	void addNode(N n);
	
	N findNode(NodeId id);
	N findNode(NodeMatcher<N> matcher);
	N findNode(String rawNodeId);

	void addEdge(N from, E edge, N to);

	void forEachNode(NodeVisitor<N> visitor);
	void forEachEdge(EdgeVisitor<N,E> visitor);

	<R extends Object> Result<R> reduceNodes(R initialValue, NodeReducer<N,R> reducer);
	<R extends Object> Result<R> recudeEdges(R initialValue, EdgeReducer<N,E,R> reducer);
	
	
	void forEachReachableNode(N from, ReachableNodeVisitor<N> visitor);
	void forEachReachableNode(N from, SO options, ReachableNodeVisitor<N> visitor);

	<R extends Object> Result<R> reduceReachableNodes(N from, R initialValue, ReachableNodeReducer<N,R> reducer);
	<R extends Object> Result<R> reduceReachableNodes(N from, SO options, R initialValue, ReachableNodeReducer<N,R> reducer);


	void forEachReachableEdge(N from, ReachableEdgeVisitor<N,E> visitor);
	void forEachReachableEdge(N from, SO options, ReachableEdgeVisitor<N,E> visitor);

	<R extends Object> Result<R> reduceReachableEdges(N from, R initialValue, ReachableEdgeReducer<N,E,R> reducer);
	<R extends Object> Result<R> reduceReachableEdges(N from, SO options, R initialValue, ReachableEdgeReducer<N,E,R> reducer);

}
