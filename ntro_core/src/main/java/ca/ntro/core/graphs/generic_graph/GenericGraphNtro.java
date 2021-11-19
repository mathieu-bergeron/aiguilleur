package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.exceptions.Break;
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
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericGraphNtro<NV extends NodeValue, EV extends EdgeValue>
       implements     GenericGraph<NV,EV> {

	@Override
	public abstract GraphId id();
	
	@Override
	public abstract String label();

	protected abstract SearchOptions defaultSearchOptions();

	protected abstract <R> void _reduceRootNodes(ResultNtro<R> result, NodeReducer<NV, R> reducer);

	protected abstract <R> void _reduceNextEdgeNames(Node<NV> fromNode, Direction direction, ResultNtro<R> result, EdgeNameReducer<R> reducer);

	protected abstract <R> void _reduceNextEdgesByName(Node<NV> fromNode, Direction direction, String edgeName, ResultNtro<R> result, ReachableEdgeReducer<NV, EV, R> reducer);
	
	

	

	@Override
	public Node<NV> findNode(NodeId id) {
		return findNode(new NodeMatcher<NV>() {
			@Override
			public boolean matches(Node<NV> node) {
				return node.id().equals(id);
			}
		});
	}

	@Override
	public Node<NV> findNode(NodeMatcher<NV> matcher) {
		
		
		
		Result<Node<NV>> result = reduceNodes(null, (accumulator, n) -> {
			if(accumulator != null) {
				throw new Break();
			}

			if(matcher.matches(n)) {
				return n;
			}

			return accumulator;
		});

		result.throwException();

		return result.value();
	}

	@Override
	public Node<NV> findNode(NV nodeValue) {
		return findNode(new NodeMatcher<NV>() {
			@Override
			public boolean matches(Node<NV> node) {
				return node.value().equals(nodeValue);
			}
		});
	}

	@Override
	public Node<NV> findNode(String rawNodeId) {
		return findNode(new NodeId(rawNodeId));
	}

	@Override
	public void forEachRootNode(NodeVisitor<NV> visitor) {
		_reduceRootNodes(null, (accumulator, n) -> {

			visitor.visitNode(n);

			return accumulator;
		});
	}


	@Override
	public <R> Result<R> reduceRootNodes(R initialValue, NodeReducer<NV, R> reducer){

		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		_reduceRootNodes(result, reducer);
		
		return result;
	}


	@Override
	public void forEachNode(NodeVisitor<NV> visitor) {
		reduceNodes(null, (accumulator, n) -> {
			
			visitor.visitNode(n);
			
			return null;
		});
		
	}

	@Override
	public <R> Result<R> reduceNodes(R initialValue, NodeReducer<NV, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		_reduceNodes(result, reducer);
		
		return result;
	}

	private <R> void _reduceNodes(ResultNtro<R> result, NodeReducer<NV, R> reducer) {

		_reduceRootNodes(result, (__, rootNode) -> {
			
			result.registerValue(reducer.reduceNode(result.value(), rootNode));
			
			_reduceReachableNodes(rootNode, result, (___, walkedEdges, n) ->{
				
				result.registerValue(reducer.reduceNode(result.value(), n));

				return null;
			});
			
			return null;
		});
		
		result.throwException();
	}

	@Override
	public void forEachEdge(EdgeVisitor<NV, EV> visitor) {
		reduceEdges(null, (accumulator, from, edge, to) -> {
			
			visitor.visitEdge(from, edge, to);
			
			return null;
		});
	}

	@Override
	public <R> Result<R> reduceEdges(R initialValue, EdgeReducer<NV, EV, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachNextNode(Node<NV> fromNode, ReachableNodeVisitor<NV, EV> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceNextNodes(Node<NV> fromNode, R initialValue, ReachableNodeReducer<NV, EV, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachReachableNode(Node<NV> fromNode, ReachableNodeVisitor<NV, EV> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachReachableNode(Node<NV> fromNode, SearchOptions options, ReachableNodeVisitor<NV, EV> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceReachableNodes(Node<NV> fromNode, R initialValue, ReachableNodeReducer<NV, EV, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	private <R> void _reduceReachableNodes(Node<NV> fromNode, ResultNtro<R> result, ReachableNodeReducer<NV, EV, R> reducer) {
	}

	@Override
	public <R> Result<R> reduceReachableNodes(Node<NV> fromNode, SearchOptions options, R initialValue,
			ReachableNodeReducer<NV, EV, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public void forEachNextEdge(Node<NV> fromNode, ReachableEdgeVisitor<NV, EV> visitor) {
		forEachNextEdge(fromNode, defaultSearchOptions(), visitor);
	}

	@Override
	public void forEachNextEdge(Node<NV> fromNode, SearchOptions options, ReachableEdgeVisitor<NV, EV> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceNextEdges(Node<NV> fromNode, R initialValue, ReachableEdgeReducer<NV, EV, R> reducer){
		return null;
	}

	@Override
	public <R> Result<R> reduceNextEdges(Node<NV> fromNode, SearchOptions options, R initialValue, ReachableEdgeReducer<NV, EV, R> reducer){
		return null;
	}

	protected <R> void _reduceNextEdges(Node<NV> fromNode, Direction direction, ResultNtro<R> result, ReachableEdgeReducer<NV, EV, R> reducer) {
		_reduceNextEdgeNames(fromNode, direction, result, (__, edgeName) -> {

			_reduceNextEdgesByName(fromNode, direction, edgeName, result, reducer);
			
			return null;
		});
	}

	@Override
	public void forEachReachableEdge(Node<NV> fromNode, ReachableEdgeVisitor<NV, EV> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachReachableEdge(Node<NV> fromNode, SearchOptions options, ReachableEdgeVisitor<NV, EV> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceReachableEdges(Node<NV> fromNode, R initialValue, ReachableEdgeReducer<NV, EV, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceReachableEdges(Node<NV> fromNode, SearchOptions options, R initialValue,
			ReachableEdgeReducer<NV, EV, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visitEdgeWalk(Node<NV> fromNode, EdgeWalk edgeWalk, EdgeWalkVisitor<NV, EV> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitEdgeWalk(Node<NV> fromNode, Direction direction, EdgeWalk edgeWalk, EdgeWalkVisitor<NV, EV> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceEdgeWalk(Node<NV> fromNode, EdgeWalk edgeWalk, R initialValue,
			EdgeWalkReducer<NV, EV, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceEdgeWalk(Node<NV> fromNode, Direction direction, EdgeWalk edgeWalk, R initialValue,
			EdgeWalkReducer<NV, EV, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}
}
