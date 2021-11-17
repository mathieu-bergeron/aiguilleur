package ca.ntro.core.reflection;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeVisitor;
import ca.ntro.core.graphs.EdgeWalkReducer;
import ca.ntro.core.graphs.EdgeWalkVisitor;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeMatcher;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeVisitor;
import ca.ntro.core.graphs.ReachableEdgeReducer;
import ca.ntro.core.graphs.ReachableEdgeVisitor;
import ca.ntro.core.graphs.ReachableNodeReducer;
import ca.ntro.core.graphs.ReachableNodeVisitor;
import ca.ntro.core.graphs.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.path.EdgeWalk;
import ca.ntro.core.wrappers.result.Result;

public class ObjectGraphJdk implements ObjectGraph {
	
	private Object object;
	
	public ObjectGraphJdk(Object object) {
		this.object = object;
	}
	

	@Override
	public GraphId id() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String label() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<ObjectValue> findNode(NodeId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<ObjectValue> findNode(NodeMatcher<ObjectValue> matcher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<ObjectValue> findNode(ObjectValue nodeValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<ObjectValue> findNode(String rawNodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachNode(NodeVisitor<ObjectValue> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachEdge(EdgeVisitor<ObjectValue, ReferenceValue> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceNodes(R initialValue, NodeReducer<ObjectValue, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> recudeEdges(R initialValue, EdgeReducer<ObjectValue, ReferenceValue, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachReachableNode(Node<ObjectValue> from, ReachableNodeVisitor<ObjectValue> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachReachableNode(Node<ObjectValue> from, DirectedGraphSearchOptions options,
			ReachableNodeVisitor<ObjectValue> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceReachableNodes(Node<ObjectValue> from, R initialValue,
			ReachableNodeReducer<ObjectValue, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceReachableNodes(Node<ObjectValue> from, DirectedGraphSearchOptions options,
			R initialValue, ReachableNodeReducer<ObjectValue, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachReachableEdge(Node<ObjectValue> from,
			ReachableEdgeVisitor<ObjectValue, ReferenceValue> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachReachableEdge(Node<ObjectValue> from, DirectedGraphSearchOptions options,
			ReachableEdgeVisitor<ObjectValue, ReferenceValue> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceReachableEdges(Node<ObjectValue> from, R initialValue,
			ReachableEdgeReducer<ObjectValue, ReferenceValue, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceReachableEdges(Node<ObjectValue> from, DirectedGraphSearchOptions options,
			R initialValue, ReachableEdgeReducer<ObjectValue, ReferenceValue, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visitEdgeWalk(Node<ObjectValue> from, EdgeWalk edgeWalk,
			EdgeWalkVisitor<ObjectValue, ReferenceValue> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitEdgeWalk(Node<ObjectValue> from, Direction direction, EdgeWalk edgeWalk,
			EdgeWalkVisitor<ObjectValue, ReferenceValue> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceEdgeWalk(Node<ObjectValue> from, EdgeWalk edgeWalk, R initialValue,
			EdgeWalkReducer<ObjectValue, ReferenceValue, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceEdgeWalk(Node<ObjectValue> from, Direction direction, EdgeWalk edgeWalk, R initialValue,
			EdgeWalkReducer<ObjectValue, ReferenceValue, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

}
