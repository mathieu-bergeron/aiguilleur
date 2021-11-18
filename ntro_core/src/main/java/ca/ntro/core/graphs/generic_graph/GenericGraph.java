package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.EdgeVisitor;
import ca.ntro.core.graphs.EdgeWalkReducer;
import ca.ntro.core.graphs.EdgeWalkVisitor;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeMatcher;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.NodeVisitor;
import ca.ntro.core.graphs.ReachableEdgeReducer;
import ca.ntro.core.graphs.ReachableEdgeVisitor;
import ca.ntro.core.graphs.ReachableNodeReducer;
import ca.ntro.core.graphs.ReachableNodeVisitor;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.path.EdgeWalk;
import ca.ntro.core.wrappers.result.Result;

public interface GenericGraph<SO extends SearchOptions, NV extends NodeValue, EV extends EdgeValue> {

	GraphId id();
	String label();
	
	Node<NV> findNode(NodeId id);
	Node<NV> findNode(NodeMatcher<NV> matcher);
	Node<NV> findNode(NV nodeValue);
	Node<NV> findNode(String rawNodeId);
	
	void forEachNode(NodeVisitor<NV> visitor);
	void forEachEdge(EdgeVisitor<NV,EV> visitor);

	<R extends Object> Result<R> reduceNodes(R initialValue, NodeReducer<NV,R> reducer);
	<R extends Object> Result<R> recudeEdges(R initialValue, EdgeReducer<NV,EV,R> reducer);

	void forEachNextNode(Node<NV> from, ReachableNodeVisitor<NV,EV> visitor);
	void forEachNextNode(Node<NV> from, SO options, ReachableNodeVisitor<NV,EV> visitor);

	<R extends Object> Result<R> reduceNextNodes(Node<NV> from, R initialValue, ReachableNodeReducer<NV,EV,R> reducer);
	<R extends Object> Result<R> reduceNextNodes(Node<NV> from, SO options, R initialValue, ReachableNodeReducer<NV,EV,R> reducer);
	
	void forEachReachableNode(Node<NV> from, ReachableNodeVisitor<NV,EV> visitor);
	void forEachReachableNode(Node<NV> from, SO options, ReachableNodeVisitor<NV,EV> visitor);

	<R extends Object> Result<R> reduceReachableNodes(Node<NV> from, R initialValue, ReachableNodeReducer<NV,EV,R> reducer);
	<R extends Object> Result<R> reduceReachableNodes(Node<NV> from, SO options, R initialValue, ReachableNodeReducer<NV,EV,R> reducer);
	
	void forEachNextEdge(Node<NV> from, ReachableEdgeVisitor<NV,EV> visitor);
	void forEachNextEdge(Node<NV> from, SO options, ReachableEdgeVisitor<NV,EV> visitor);

	<R extends Object> Result<R> reduceNextEdges(Node<NV> from, R initialValue, ReachableEdgeReducer<NV,EV,R> reducer);
	<R extends Object> Result<R> reduceNextEdges(Node<NV> from, SO options, R initialValue, ReachableEdgeReducer<NV,EV,R> reducer);

	void forEachReachableEdge(Node<NV> from, ReachableEdgeVisitor<NV,EV> visitor);
	void forEachReachableEdge(Node<NV> from, SO options, ReachableEdgeVisitor<NV,EV> visitor);

	<R extends Object> Result<R> reduceReachableEdges(Node<NV> from, R initialValue, ReachableEdgeReducer<NV,EV,R> reducer);
	<R extends Object> Result<R> reduceReachableEdges(Node<NV> from, SO options, R initialValue, ReachableEdgeReducer<NV,EV,R> reducer);

	void visitEdgeWalk(Node<NV> from, EdgeWalk edgeWalk, EdgeWalkVisitor<NV,EV> visitor);
	void visitEdgeWalk(Node<NV> from, Direction direction, EdgeWalk edgeWalk, EdgeWalkVisitor<NV,EV> visitor);

	<R extends Object> Result<R> reduceEdgeWalk(Node<NV> from, EdgeWalk edgeWalk, R initialValue, EdgeWalkReducer<NV,EV,R> reducer);
	<R extends Object> Result<R> reduceEdgeWalk(Node<NV> from, Direction direction, EdgeWalk edgeWalk, R initialValue, EdgeWalkReducer<NV,EV,R> reducer);

}
