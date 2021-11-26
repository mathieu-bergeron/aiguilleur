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
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.path.EdgeWalk;
import ca.ntro.core.wrappers.result.Result;

public interface GenericGraph<NV extends NodeValue, EV extends EdgeValue> {

	GraphId id();
	String label();

	void write(GraphWriter writer);
	
	Node<NV> findNode(NodeId id);
	Node<NV> findNode(NodeMatcher<NV> matcher);
	Node<NV> findNode(NV nodeValue);
	Node<NV> findNode(String rawNodeId);

	void forEachStartNode(NodeVisitor<NV> visitor);
	<R extends Object> Result<R> reduceStartNodes(R initialValue, NodeReducer<NV,R> reducer);

	void forEachNode(NodeVisitor<NV> visitor);
	<R extends Object> Result<R> reduceNodes(R initialValue, NodeReducer<NV,R> reducer);

	void forEachEdge(EdgeVisitor<NV,EV> visitor);
	<R extends Object> Result<R> reduceEdges(R initialValue, EdgeReducer<NV,EV,R> reducer);

	void forEachNextNode(Node<NV> fromNode, ReachableNodeVisitor<NV,EV> visitor);
	void forEachNextNode(Node<NV> fromNode, SearchOptions options, ReachableNodeVisitor<NV,EV> visitor);

	<R extends Object> Result<R> reduceNextNodes(Node<NV> fromNode, R initialValue, ReachableNodeReducer<NV,EV,R> reducer);
	<R extends Object> Result<R> reduceNextNodes(Node<NV> fromNode, SearchOptions options, R initialValue, ReachableNodeReducer<NV,EV,R> reducer);

	void forEachReachableNode(Node<NV> fromNode, ReachableNodeVisitor<NV,EV> visitor);
	void forEachReachableNode(Node<NV> fromNode, SearchOptions options, ReachableNodeVisitor<NV,EV> visitor);

	<R extends Object> Result<R> reduceReachableNodes(Node<NV> fromNode, R initialValue, ReachableNodeReducer<NV,EV,R> reducer);
	<R extends Object> Result<R> reduceReachableNodes(Node<NV> fromNode, SearchOptions options, R initialValue, ReachableNodeReducer<NV,EV,R> reducer);
	
	void forEachNextEdge(Node<NV> fromNode, ReachableEdgeVisitor<NV,EV> visitor);
	void forEachNextEdge(Node<NV> fromNode, SearchOptions options, ReachableEdgeVisitor<NV,EV> visitor);

	<R extends Object> Result<R> reduceNextEdges(Node<NV> fromNode, R initialValue, ReachableEdgeReducer<NV,EV,R> reducer);
	<R extends Object> Result<R> reduceNextEdges(Node<NV> fromNode, SearchOptions options, R initialValue, ReachableEdgeReducer<NV,EV,R> reducer);

	void forEachReachableEdge(Node<NV> fromNode, ReachableEdgeVisitor<NV,EV> visitor);
	void forEachReachableEdge(Node<NV> fromNode, SearchOptions options, ReachableEdgeVisitor<NV,EV> visitor);

	<R extends Object> Result<R> reduceReachableEdges(Node<NV> fromNode, R initialValue, ReachableEdgeReducer<NV,EV,R> reducer);
	<R extends Object> Result<R> reduceReachableEdges(Node<NV> fromNode, SearchOptions options, R initialValue, ReachableEdgeReducer<NV,EV,R> reducer);

	void visitEdgeWalk(Node<NV> fromNode, EdgeWalk edgeWalk, EdgeWalkVisitor<NV,EV> visitor);
	void visitEdgeWalk(Node<NV> fromNode, Direction direction, EdgeWalk edgeWalk, EdgeWalkVisitor<NV,EV> visitor);

	<R extends Object> Result<R> reduceEdgeWalk(Node<NV> fromNode, EdgeWalk edgeWalk, R initialValue, EdgeWalkReducer<NV,EV,R> reducer);
	<R extends Object> Result<R> reduceEdgeWalk(Node<NV> fromNode, Direction direction, EdgeWalk edgeWalk, R initialValue, EdgeWalkReducer<NV,EV,R> reducer);

	
}
