package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.path.EdgeWalk;
import ca.ntro.core.wrappers.result.Result;

public interface GenericGraph<SO extends SearchOptions, NV extends NodeValue, EV extends EdgeValue> {

	GraphId id();
	String label();
	
	Node<NV> findNode(NodeId id);
	Node<NV> findNode(NodeMatcher<NV> matcher);
	Node<NV> findNode(NV nodeValue);
	Node<NV> findNode(String rawNodeId);

	Node<NV> walkToNode(Node<NV> from, String rawEdgeWalk);
	Node<NV> walkToNode(Node<NV> from, EdgeId[] edgeWalk);
	Node<NV> walkToNode(Node<NV> from, EdgeWalk edgeWalk);

	void forEachNode(NodeVisitor<Node<NV>> visitor);
	void forEachEdge(EdgeVisitor<Node<NV>,Edge<EV>> visitor);

	<R extends Object> Result<R> reduceNodes(R initialValue, NodeReducer<Node<NV>,R> reducer);
	<R extends Object> Result<R> recudeEdges(R initialValue, EdgeReducer<Node<NV>,Edge<EV>,R> reducer);
	
	
	void forEachReachableNode(Node<NV> from, ReachableNodeVisitor<Node<NV>> visitor);
	void forEachReachableNode(Node<NV> from, SO options, ReachableNodeVisitor<Node<NV>> visitor);

	<R extends Object> Result<R> reduceReachableNodes(Node<NV> from, R initialValue, ReachableNodeReducer<Node<NV>,R> reducer);
	<R extends Object> Result<R> reduceReachableNodes(Node<NV> from, SO options, R initialValue, ReachableNodeReducer<Node<NV>,R> reducer);


	void forEachReachableEdge(Node<NV> from, ReachableEdgeVisitor<Node<NV>,Edge<EV>> visitor);
	void forEachReachableEdge(Node<NV> from, SO options, ReachableEdgeVisitor<Node<NV>,Edge<EV>> visitor);

	<R extends Object> Result<R> reduceReachableEdges(Node<NV> from, R initialValue, ReachableEdgeReducer<Node<NV>,Edge<EV>,R> reducer);
	<R extends Object> Result<R> reduceReachableEdges(Node<NV> from, SO options, R initialValue, ReachableEdgeReducer<Node<NV>,Edge<EV>,R> reducer);

}
