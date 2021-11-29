package ca.ntro.core.graphs.generic_graph;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeVisitor;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.ReachableNodeReducer;
import ca.ntro.core.graphs.ReachableNodeVisitor;
import ca.ntro.core.graphs.ReachableStepReducer;
import ca.ntro.core.graphs.ReachableStepVisitor;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.SearchStrategy;
import ca.ntro.core.graphs.WalkReducer;
import ca.ntro.core.graphs.WalkVisitor;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class NodeNtro<N extends Node<N,E,SO>, 
                               E extends Edge<N,E,SO>,
                               SO extends SearchOptions> 

      implements      Node<N,E,SO> {
	
	private NodeId nodeId;

	public NodeId getNodeId() {
		return nodeId;
	}

	public void setNodeId(NodeId nodeId) {
		this.nodeId = nodeId;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(o instanceof NodeNtro) {
			NodeNtro n = (NodeNtro) o;
			
			if(n.nodeId == null && nodeId != null) {
				return false;
			}

			if(n.nodeId != null && !n.nodeId.equals(nodeId)) {
				return false;
			}
			
			return true;
		}
		
		return false;
	}
	
	public NodeNtro(NodeId nodeId) {
		setNodeId(nodeId);
	}

	@Override
	public NodeId id() {
		return getNodeId();
	}

	protected abstract <R> void _reduceEdgeTypes(ResultNtro<R> result, EdgeTypeReducer<R> reducer);
	protected abstract <R> void _reduceEdgesByType(EdgeType edgeType, ResultNtro<R> result, EdgeReducer<N,E,SO,R> reducer);

	@Override
	public void forEachEdge(EdgeVisitor<N,E,SO> visitor) {
		reduceEdges(null, (__, e) -> {
			
			visitor.visitEdge(e);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceEdges(R initialValue, EdgeReducer<N, E, SO, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		_reduceEdgeTypes(result, (__, edgeType) -> {

			_reduceEdgesByType(edgeType, result, reducer);
			
			return result.value();
		});
		
		return result;
	}

	protected abstract SO defaultSearchOptions();

	@Override
	public void forEachReachableNode(ReachableNodeVisitor<N,E,SO> visitor) {
		forEachReachableNode(defaultSearchOptions(), visitor);
	}

	@Override
	public void forEachReachableNode(SO options, ReachableNodeVisitor<N,E,SO> visitor) {
		reduceReachableNodes(options, null, (accumulator, walkedSteps, n) -> {

			visitor.visitReachableNode(walkedSteps, n);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceReachableNodes(R initialValue, ReachableNodeReducer<N,E,SO,R> reducer) {
		return reduceReachableNodes(defaultSearchOptions(), initialValue, reducer);
	}

	@Override
	public <R> Result<R> reduceReachableNodes(SO options, R initialValue, ReachableNodeReducer<N,E,SO,R> reducer) {

		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		_reduceReachableNodes(options, result, reducer);
		
		return result;
	}

	protected <R> void _reduceReachableNodes(SearchOptions options, 
			                                 ResultNtro<R> result, 
			                                 ReachableNodeReducer<N,E,SO,R> reducer) {

		if(result.hasException()) {
			return;
		}
		
		Set<String> visitedNodes = new HashSet<>();
		
		forEachReachableEdge((walked, edge) -> {
			if(visitedNodes.contains(edge.to().id().toKey().toString())) {
				return;
			}

			try {
				
				result.registerValue(reducer.reduceReachableNode(result.value(), walked, edge.to()));

			} catch(Throwable t) {
				
				result.registerException(t);
				throw new Break();
			}

			visitedNodes.add(edge.to().id().toKey().toString());
		});
	}

	@Override
	public void forEachReachableEdge(ReachableStepVisitor<N,E,SO> visitor) {
		forEachReachableEdge(defaultSearchOptions(), visitor);
	}

	@Override
	public void forEachReachableEdge(SO options, ReachableStepVisitor<N,E,SO> visitor) {
		reduceReachableEdges(options, null, (accumulator, walked, edge) -> {

			visitor.visitReachableEdge(walked, edge);

			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceReachableEdges(R initialValue, ReachableStepReducer<N,E,SO,R> reducer) {
		return reduceReachableEdges(defaultSearchOptions(), initialValue, reducer);
	}

	@Override
	public <R> Result<R> reduceReachableEdges(SO options, R initialValue, ReachableStepReducer<N,E,SO,R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		_reduceReachableEdges(options, result, reducer);
		
		return result;
	}

	protected <R> void _reduceReachableEdges(SearchOptions options, 
			                                 ResultNtro<R> result, 
			                                 ReachableStepReducer<N,E,SO,R> reducer) {
		
		if(options.searchStrategy() == SearchStrategy.DEPTH_FIRST_SEARCH) {

			_reduceReachableEdgesDepthFirst(options, new HashSet<>(), result, reducer);

		}else {

			_reduceReachableEdgesBreadthFirst(options, new HashSet<>(), result, reducer);
		}
	}

	@SuppressWarnings("unchecked")
	protected <R> void _reduceReachableEdgesDepthFirst(SearchOptions options, 
			                                           Set<String> visitedEdges,
			                                           ResultNtro<R> result, 
			                                           ReachableStepReducer<N,E,SO,R> reducer) {

		if(result.hasException()) {
			return;
		}

		_reduceEdgeTypes(result, (__, edgeType) -> {
			
			_reduceEdgesByType(edgeType, result, (___, edge) -> {
				if(visitedEdges.contains(edge.id().toKey().toString())) {
					return result.value();
				}
				
				Walk<N,E,SO> walked = new WalkNtro<>();
				walked.add(edge);

				if(options.maxDistance().hasValue() 
						&& walked.size() > options.maxDistance().value()) {
					return result.value();
				}
				
				try {
				
					result.registerValue(reducer.reduceWalkedStep(result.value(), walked, edge));

				}catch(Throwable t) {
					
					result.registerException(t);
					return result.value();
				}
				
				visitedEdges.add(edge.id().toKey().toString());

				((NodeNtro<N,E,SO>) edge.to())._reduceReachableEdgesDepthFirst(options, visitedEdges, result, reducer);

				return result.value();
			});
			
			return result.value();
		});
	}

	protected <R> void _reduceReachableEdgesBreadthFirst(SearchOptions options, 
			                                             Set<String> visitedEdges,
			                                             ResultNtro<R> result, 
			                                             ReachableStepReducer<N,E,SO,R> reducer) {
		
		SearchOptions oneStepOptions = new SearchOptionsNtro(options.searchDirections(), 1);
		
		_reduceReachableEdgesBreadthFirst(options, oneStepOptions, visitedEdges, new WalkNtro<N,E,SO>(), result, reducer);
	}

	@SuppressWarnings("unchecked")
	protected <R> void _reduceReachableEdgesBreadthFirst(SearchOptions options, 
			                                             SearchOptions oneStepOptions,
			                                             Set<String> visitedEdges,
			                                             Walk<N,E,SO> walked,
			                                             ResultNtro<R> result, 
			                                             ReachableStepReducer<N,E,SO,R> reducer) {

		if(options.maxDistance().hasValue() 
				&& walked.size() + 1 > options.maxDistance().value()) {
			return;
		}

		_reduceReachableEdgesDepthFirst(oneStepOptions, visitedEdges, result, reducer);

		_reduceReachableEdgesDepthFirst(oneStepOptions, visitedEdges, result, (__, ___, edge) -> {
			
			Walk<N,E,SO> newWalked = new WalkNtro<>(walked);
			newWalked.add(edge);

			((NodeNtro<N,E,SO>) edge.to())._reduceReachableEdgesBreadthFirst(options, oneStepOptions, visitedEdges, newWalked, result, reducer);
			
			return result.value();
		});
	}

	@Override
	public void visitWalk(WalkId walk, WalkVisitor<N, E, SO> visitor) {
		reduceWalk(walk, null, (accumulator, walked, remainingWalk, n) -> {
			
			visitor.visitStep(walked, remainingWalk, n);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceWalk(WalkId walk, R initialValue, WalkReducer<N,E,SO,R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		_reduceWalk(new WalkNtro<>(), walk, result, reducer);

		return result;
	}

	@SuppressWarnings("unchecked")
	protected <R> void _reduceWalk(Walk<N,E,SO> walked,
			                       WalkId walk,
			                       ResultNtro<R> result, 
			                       WalkReducer<N,E,SO,R> reducer) {
		
		if(result.hasException()) {
			return;
		}
		
		if(walk.isEmpty()) {
			return;
		}
		
		EdgeType edgeType = walk.get(0);
		WalkId remainingWalk = (WalkId) walk.subWalk(1);
		
		_reduceEdgesByType(edgeType, result, (__, edge) -> {

			try {
				
				Walk<N,E,SO> newWalked = new WalkNtro<>(walked);
				newWalked.add(edge);

				result.registerValue(reducer.reduceStep(result.value(), newWalked, remainingWalk, edge.to()));
				
				((NodeNtro<N,E,SO>) edge.to())._reduceWalk(newWalked, remainingWalk, result, reducer);

			}catch(Throwable t) {
				
				result.registerException(t);
				
			}
			
			return result.value();
		});
	}

}
