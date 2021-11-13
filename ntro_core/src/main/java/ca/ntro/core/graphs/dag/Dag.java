package ca.ntro.core.graphs.dag;

import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.dag.directions.Direction;
import ca.ntro.core.graphs.dag.exceptions.CycleException;
import ca.ntro.core.graphs.dag.exceptions.NodeNotFoundException;
import ca.ntro.core.wrappers.Result;

public interface Dag<N extends Node, E extends Edge> {
	
	GraphId id();
	String label();

	void addNode(N n);
	
	N findNode(NodeId id) throws NodeNotFoundException;
	N findNode(NodeMatcher<N> matcher) throws NodeNotFoundException;
	N findNode(String rawNodeId) throws NodeNotFoundException;

	void addEdge(N from, E edge, N to) throws CycleException;

	void forEachNode(NodeVisitor<N> visitor);
	void forEachEdge(EdgeVisitor<N,E> visitor);

	<R extends Object> Result<R> foldEachNode(R initialValue, NodeReducer<N,R> visitor);
	<R extends Object> Result<R> foldEachEdge(R initialValue, EdgeFolder<N,E,R> visitor);
	
	void forEachReachableNode(N from, NodeVisitor<N> visitor);
	void forEachReachableNode(N from, Direction[] directions, NodeVisitor<N> visitor);
	void forEachReachableNode(N from, Direction[] directions, SearchStrategy searchStrategy, NodeVisitor<N> visitor);

	<R extends Object> Result<R> reduceReachableNodes(N from, R initialValue, NodeReducer<N,R> reducer);
	<R extends Object> Result<R> reduceReachableNodes(N from, Direction[] directions, R initialValue, NodeReducer<N,R> reducer);
	<R extends Object> Result<R> reduceReachableNodes(N from, Direction[] directions, SearchStrategy searchStrategy, R initialValue, NodeReducer<N,R> reducer);

	void write(DagWriter<N,E> writer);
}
