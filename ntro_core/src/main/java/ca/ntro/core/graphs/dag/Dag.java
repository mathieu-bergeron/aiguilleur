package ca.ntro.core.graphs.dag;

import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.dag.directions.Direction;
import ca.ntro.core.graphs.graph.Edge;
import ca.ntro.core.graphs.graph.EdgeReducer;
import ca.ntro.core.graphs.graph.EdgeVisitor;
import ca.ntro.core.graphs.graph.Node;
import ca.ntro.core.graphs.graph.NodeId;
import ca.ntro.core.graphs.graph.NodeMatcher;
import ca.ntro.core.graphs.graph.NodeReducer;
import ca.ntro.core.graphs.graph.NodeVisitor;
import ca.ntro.core.graphs.graph.SearchStrategy;
import ca.ntro.core.wrappers.result.Result;

public interface Dag<N extends Node, E extends Edge> {
	
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
	
	void forEachReachableNode(N from, NodeVisitor<N> visitor);
	void forEachReachableNode(N from, Direction[] directions, NodeVisitor<N> visitor);
	void forEachReachableNode(N from, Direction[] directions, SearchStrategy searchStrategy, NodeVisitor<N> visitor);

	<R extends Object> Result<R> reduceReachableNodes(N from, R initialValue, NodeReducer<N,R> reducer);
	<R extends Object> Result<R> reduceReachableNodes(N from, Direction[] directions, R initialValue, NodeReducer<N,R> reducer);
	<R extends Object> Result<R> reduceReachableNodes(N from, Direction[] directions, SearchStrategy searchStrategy, R initialValue, NodeReducer<N,R> reducer);

}
