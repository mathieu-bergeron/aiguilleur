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

public abstract class GenericGraphNtro<SO extends SearchOptions, NV extends NodeValue, EV extends EdgeValue>
       implements     GenericGraph<SO,NV,EV> {

	@Override
	public abstract GraphId id();
	
	@Override
	public abstract String label();


	protected abstract SO defaultSearchOptions();

	@Override
	public abstract <R> Result<R> reduceRootNodes(R initialValue, NodeReducer<NV, R> reducer);
	
	protected abstract <R> Result<R> reduceNextEdges(Node<NV> from, Direction direction, R initialValue, ReachableEdgeReducer<NV, EV, R> reducer);

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
		reduceRootNodes(null, (accumulator, n) -> {

			visitor.visitNode(n);

			return null;
		});
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
		
		return reduceRootNodes(initialValue, (accumulator, rootNode) -> {
			
			accumulator = reducer.reduceNode(accumulator, rootNode);
			
			Result<R> result = reduceReachableNodes(rootNode, accumulator, (innerAccumulator, walkedEdges, n) ->{
				
				innerAccumulator = reducer.reduceNode(innerAccumulator, n);

				return innerAccumulator;
			});
			
			result.throwException();
			
			accumulator = result.value();
			
			return accumulator;
		});
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
	public void forEachNextNode(Node<NV> from, ReachableNodeVisitor<NV, EV> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceNextNodes(Node<NV> from, R initialValue, ReachableNodeReducer<NV, EV, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachReachableNode(Node<NV> from, ReachableNodeVisitor<NV, EV> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachReachableNode(Node<NV> from, SO options, ReachableNodeVisitor<NV, EV> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceReachableNodes(Node<NV> from, R initialValue, ReachableNodeReducer<NV, EV, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceReachableNodes(Node<NV> from, SO options, R initialValue,
			ReachableNodeReducer<NV, EV, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public void forEachNextEdge(Node<NV> from, ReachableEdgeVisitor<NV, EV> visitor) {
		forEachNextEdge(from, defaultSearchOptions(), visitor);
	}

	@Override
	public void forEachNextEdge(Node<NV> from, SO options, ReachableEdgeVisitor<NV, EV> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceNextEdges(Node<NV> from, R initialValue, ReachableEdgeReducer<NV, EV, R> reducer){
		return null;
	}

	@Override
	public <R> Result<R> reduceNextEdges(Node<NV> from, SO options, R initialValue, ReachableEdgeReducer<NV, EV, R> reducer){
		return null;
	}


	@Override
	public void forEachReachableEdge(Node<NV> from, ReachableEdgeVisitor<NV, EV> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachReachableEdge(Node<NV> from, SO options, ReachableEdgeVisitor<NV, EV> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceReachableEdges(Node<NV> from, R initialValue, ReachableEdgeReducer<NV, EV, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceReachableEdges(Node<NV> from, SO options, R initialValue,
			ReachableEdgeReducer<NV, EV, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visitEdgeWalk(Node<NV> from, EdgeWalk edgeWalk, EdgeWalkVisitor<NV, EV> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitEdgeWalk(Node<NV> from, Direction direction, EdgeWalk edgeWalk, EdgeWalkVisitor<NV, EV> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceEdgeWalk(Node<NV> from, EdgeWalk edgeWalk, R initialValue,
			EdgeWalkReducer<NV, EV, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceEdgeWalk(Node<NV> from, Direction direction, EdgeWalk edgeWalk, R initialValue,
			EdgeWalkReducer<NV, EV, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}
}
