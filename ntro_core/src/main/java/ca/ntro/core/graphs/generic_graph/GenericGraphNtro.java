package ca.ntro.core.graphs.generic_graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.SearchStrategy;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.path.EdgeWalk;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;
import ca.ntro.core.graphs.Edge;

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
		reduceRootNodes(null, (__, n) ->{
			
			visitor.visitNode(n);
			
			return null;

		}).throwException();;
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

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceNodes(R initialValue, NodeReducer<NV, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		_reduceNodes(result, reducer);
		
		return result;
	}

	protected <R> void _reduceNodes(ResultNtro<R> result, NodeReducer<NV, R> reducer) {
		
		_reduceRootNodes(result, (__, rootNode) -> {
			
			try {
			
				result.registerValue(reducer.reduceNode(result.value(), rootNode));

			}catch(Throwable t) {
				
				result.registerException(t);
			}

			_reduceReachableNodes(rootNode, defaultSearchOptions(), result, (___, walkedEdges, n) ->{
				
				try {
				
					result.registerValue(reducer.reduceNode(result.value(), n));

				}catch(Throwable t) {
					
					result.registerException(t);
				}

				return result.value();
			});
			
			return result.value();
		});
	}

	@Override
	public void forEachEdge(EdgeVisitor<NV, EV> visitor) {
		reduceEdges(null, (accumulator, from, edge, to) -> {
			
			visitor.visitEdge(from, edge, to);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceEdges(R initialValue, EdgeReducer<NV, EV, R> reducer) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		_reduceEdges(result, reducer);
		
		return result;
	}

	protected <R> void _reduceEdges(ResultNtro<R> result, EdgeReducer<NV, EV, R> reducer) {
		if(result.hasException()) {
			return;
		}
		
		Set<String> visitedEdges = new HashSet<>();

		_reduceRootNodes(result, (__, n) -> {

			_reduceReachableEdges(n, defaultSearchOptions(), result, (___, walkedEdges, from, edge, to) -> {
				
				if(!visitedEdges.contains(edge.id().toKey())) {
					
					try {

						result.registerValue(reducer.reduceEdge(result.value(), from, edge, to));

					}catch(Throwable t) {
						
						result.registerException(t);
					}
					
					visitedEdges.add(edge.id().toKey());
				}
				
				return result.value();
			});

			return result.value();
		});
	}

	@Override
	public void forEachNextNode(Node<NV> fromNode, ReachableNodeVisitor<NV, EV> visitor) {
		forEachNextNode(fromNode, defaultSearchOptions(), visitor);
	}

	@Override
	public void forEachNextNode(Node<NV> fromNode, SearchOptions options, ReachableNodeVisitor<NV, EV> visitor) {
		reduceNextNodes(fromNode, options, null, (accumulator, walkedEdges, n) -> {

			visitor.visitReachableNode(walkedEdges, fromNode);

			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceNextNodes(Node<NV> fromNode, R initialValue, ReachableNodeReducer<NV, EV, R> reducer) {
		return reduceNextNodes(fromNode, defaultSearchOptions(), initialValue, reducer);
	}

	@Override
	public <R> Result<R> reduceNextNodes(Node<NV> fromNode, SearchOptions options, R initialValue, ReachableNodeReducer<NV, EV, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		_reduceNextNodes(fromNode, options, result, reducer);

		return result;
	}

	protected <R> void _reduceNextNodes(Node<NV> fromNode, SearchOptions options, ResultNtro<R> result, ReachableNodeReducer<NV, EV, R> reducer) {
		_reduceNextEdges(fromNode, options, result, (accumulator, walkedEdges, from, edge, to) -> {
			
			try {

				result.registerValue(reducer.reduceReachableNode(result.value(), walkedEdges, to));

			}catch(Throwable t) {
				
				result.registerException(t);
			}
			
			return result.value();
		});
	}

	@Override
	public void forEachReachableNode(Node<NV> fromNode, ReachableNodeVisitor<NV, EV> visitor) {
		forEachReachableNode(fromNode, defaultSearchOptions(), visitor);
	}

	@Override
	public void forEachReachableNode(Node<NV> fromNode, SearchOptions options, ReachableNodeVisitor<NV, EV> visitor) {
		reduceReachableNodes(fromNode, options, null, (accumulator, walkedEdges, n) -> {

			visitor.visitReachableNode(walkedEdges, n);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceReachableNodes(Node<NV> fromNode, 
			                                  R initialValue, 
			                                  ReachableNodeReducer<NV, EV, R> reducer) {
		
		return reduceReachableNodes(fromNode, defaultSearchOptions(), initialValue, reducer);
	}

	@Override
	public <R> Result<R> reduceReachableNodes(Node<NV> fromNode, 
			                                  SearchOptions options, 
			                                  R initialValue, 
			                                  ReachableNodeReducer<NV, EV, R> reducer) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		_reduceReachableNodes(fromNode, options, result, reducer);
		
		
		return result;
	}

	protected <R> void _reduceReachableNodes(Node<NV> fromNode, 
			                                 SearchOptions options, 
			                                 ResultNtro<R> result, 
			                                 ReachableNodeReducer<NV, EV, R> reducer) {

		if(result.hasException()) {
			return;
		}
		
		Set<String> visitedNodes = new HashSet<>();
		
		_reduceReachableEdges(fromNode, options, result, (__, walkedEdges, from, edge, to) -> {
			
			if(!visitedNodes.contains(to.id().toKey())) {
				
				try {
					
					result.registerValue(reducer.reduceReachableNode(result.value(), walkedEdges, to));

				} catch(Throwable t) {
					
					result.registerException(t);
					return result.value();
				}

				visitedNodes.add(to.id().toKey());
			}
			
			return result.value();
		});
	}
	

	@Override
	public void forEachNextEdge(Node<NV> fromNode, ReachableEdgeVisitor<NV, EV> visitor) {
		forEachNextEdge(fromNode, defaultSearchOptions(), visitor);
	}

	@Override
	public void forEachNextEdge(Node<NV> fromNode, SearchOptions options, ReachableEdgeVisitor<NV, EV> visitor) {

		reduceNextEdges(fromNode, options, null, (accumulator, walkedEdges, from, edge, to) -> {

			visitor.visitReachableEdge(walkedEdges, fromNode, edge, to);

			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceNextEdges(Node<NV> fromNode, R initialValue, ReachableEdgeReducer<NV, EV, R> reducer){
		return reduceNextEdges(fromNode, defaultSearchOptions(), initialValue, reducer);
	}

	@Override
	public <R> Result<R> reduceNextEdges(Node<NV> fromNode, SearchOptions options, R initialValue, ReachableEdgeReducer<NV, EV, R> reducer){
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		_reduceNextEdges(fromNode, options, result, reducer);
		
		return result;
	}

	protected <R> void _reduceNextEdges(Node<NV> fromNode, SearchOptions options, ResultNtro<R> result, ReachableEdgeReducer<NV, EV, R> reducer) {
		
		for(Direction direction : options.searchDirections()) {
			
			if(result.hasException()) {
				return;
			}

			_reduceNextEdgeNames(fromNode, direction, result, (__, edgeName) -> {

				_reduceNextEdgesByName(fromNode, direction, edgeName, result, reducer);
				
				return result.value();
			});
		}
	}


	@Override
	public void forEachReachableEdge(Node<NV> fromNode, ReachableEdgeVisitor<NV, EV> visitor) {
		forEachReachableEdge(fromNode, defaultSearchOptions(), visitor);
	}

	@Override
	public void forEachReachableEdge(Node<NV> fromNode, SearchOptions options, ReachableEdgeVisitor<NV, EV> visitor) {
		reduceReachableEdges(fromNode, options, null, (accumulator, walkedEdges, from, edge, to) -> {
			
			visitor.visitReachableEdge(walkedEdges, fromNode, edge, to);

			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceReachableEdges(Node<NV> fromNode, R initialValue, ReachableEdgeReducer<NV, EV, R> reducer) {
		
		return reduceReachableEdges(fromNode, defaultSearchOptions(), initialValue, reducer);
	}


	@Override
	public <R> Result<R> reduceReachableEdges(Node<NV> fromNode, 
			                                  SearchOptions options, 
			                                  R initialValue, 
			                                  ReachableEdgeReducer<NV, EV, R> reducer) {

		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		_reduceReachableEdges(fromNode, options, result, reducer);
		
		return result;
	}

	protected <R> void _reduceReachableEdges(Node<NV> fromNode, 
			                                 SearchOptions options, 
			                                 ResultNtro<R> result, 
			                                 ReachableEdgeReducer<NV, EV, R> reducer) {
		
		if(options.searchStrategy() == SearchStrategy.DEPTH_FIRST_SEARCH) {

			_reduceReachableEdgesDepthFirst(fromNode, options, new HashSet<>(), result, reducer);

		}else {

			_reduceReachableEdgesBreadthFirst(fromNode, options, new HashSet<>(), result, reducer);
		}
	}

	protected <R> void _reduceReachableEdgesDepthFirst(Node<NV> fromNode, 
			                                           SearchOptions options, 
			                                           Set<String> visitedEdges,
			                                           ResultNtro<R> result, 
			                                           ReachableEdgeReducer<NV, EV, R> reducer) {
		
		for(Direction direction : options.searchDirections()) {
			
			if(result.hasException()) {
				return;
			}

			_reduceNextEdgeNames(fromNode, direction, result, (__, edgeName) -> {
				
				_reduceNextEdgesByName(fromNode, direction, edgeName, result, (___, walkedEdges, from, edge, to) -> {
					if(visitedEdges.contains(edge.id().toKey())) {
						return result.value();
					}

					List<Edge<EV>> newWalkedEdges = new ArrayList<Edge<EV>>(walkedEdges);
					newWalkedEdges.add(edge);

					if(options.maxDistance().hasValue() 
							&& newWalkedEdges.size() > options.maxDistance().value()) {
						return result.value();
					}
					
					try {
					
						result.registerValue(reducer.reduceReachableEdge(result.value(), newWalkedEdges, from, edge, to));

					}catch(Throwable t) {
						
						result.registerException(t);
						return result.value();
					}
					
					visitedEdges.add(edge.id().toKey());

					_reduceReachableEdgesDepthFirst(to, options, visitedEdges, result, reducer);

					return result.value();
				});
				
				return result.value();
			});
		}
	}

	protected <R> void _reduceReachableEdgesBreadthFirst(Node<NV> fromNode, 
			                                             SearchOptions options, 
			                                             Set<String> visitedEdges,
			                                             ResultNtro<R> result, 
			                                             ReachableEdgeReducer<NV, EV, R> reducer) {
		
		SearchOptions oneStepOptions = new SearchOptionsNtro(options.searchDirections(), 1);
		
		_reduceReachableEdgesBreadthFirst(fromNode, options, oneStepOptions, visitedEdges, new ArrayList<>(), result, reducer);
	}

	protected <R> void _reduceReachableEdgesBreadthFirst(Node<NV> fromNode, 
			                                             SearchOptions options, 
			                                             SearchOptions oneStepOptions,
			                                             Set<String> visitedEdges,
			                                             List<Edge<EV>> walkedEdges,
			                                             ResultNtro<R> result, 
			                                             ReachableEdgeReducer<NV, EV, R> reducer) {

		if(options.maxDistance().hasValue() 
				&& walkedEdges.size() + 1 > options.maxDistance().value()) {
			return;
		}

		_reduceReachableEdgesDepthFirst(fromNode, oneStepOptions, visitedEdges, result, reducer);

		_reduceReachableEdgesDepthFirst(fromNode, oneStepOptions, visitedEdges, result, (__, ___, from, edge, to) -> {

			List<Edge<EV>> newWalkedEdges = new ArrayList<Edge<EV>>(walkedEdges);
			newWalkedEdges.add(edge);

			_reduceReachableEdgesBreadthFirst(to, options, oneStepOptions, visitedEdges, newWalkedEdges, result, reducer);
			
			return result.value();
		});
	}

	@Override
	public void visitEdgeWalk(Node<NV> fromNode, EdgeWalk edgeWalk, EdgeWalkVisitor<NV, EV> visitor) {
		visitEdgeWalk(fromNode, defaultSearchOptions().searchDirections()[0], edgeWalk, visitor);
	}

	@Override
	public void visitEdgeWalk(Node<NV> fromNode, Direction direction, EdgeWalk edgeWalk, EdgeWalkVisitor<NV, EV> visitor) {

		reduceEdgeWalk(fromNode, direction, edgeWalk, null, (accumulator, walkedEdges, remainingEdgeWalk, n) -> {
			
			visitor.visitEdgeWalk(walkedEdges, remainingEdgeWalk, n);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceEdgeWalk(Node<NV> fromNode, 
			                            EdgeWalk edgeWalk, 
			                            R initialValue, 
			                            EdgeWalkReducer<NV, EV, R> reducer) {
		
		return reduceEdgeWalk(fromNode, 
				              defaultSearchOptions().walkDirection(), 
				              edgeWalk, 
				              initialValue, 
				              reducer);
	}

	@Override
	public <R> Result<R> reduceEdgeWalk(Node<NV> fromNode, 
			                            Direction direction, 
			                            EdgeWalk edgeWalk, 
			                            R initialValue, 
			                            EdgeWalkReducer<NV, EV, R> reducer) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		_reduceEdgeWalk(fromNode, direction, edgeWalk, result, reducer);

		return result;
	}

	protected <R> void _reduceEdgeWalk(Node<NV> fromNode, 
			                           Direction direction, 
			                           EdgeWalk edgeWalk, 
			                           ResultNtro<R> result, 
			                           EdgeWalkReducer<NV, EV, R> reducer) {
		
		if(result.hasException()) {
			return;
		}
		
		if(edgeWalk.isRootPath()) {
			return;
		}
		
		String nextEdgeName = edgeWalk.name(0);
		EdgeWalk remainingEdgeWalk = edgeWalk.subPath(0);
		
		_reduceNextEdgesByName(fromNode, direction, nextEdgeName, result, (__, walkedEdges, from, edge, to) -> {

			try {

				result.registerValue(reducer.reduceEdgeWalk(result.value(), walkedEdges, remainingEdgeWalk, to));
				
				_reduceEdgeWalk(to, direction, remainingEdgeWalk, result, reducer);

			}catch(Throwable t) {
				
				result.registerException(t);
				
			}
			
			return result.value();
		});
	}
	
	
	@Override
	public void write(GraphWriter writer) {

		writer.initialize(id());
		
		Set<String> unwrittenNodes = writeEdges(writer);

		writeNodes(writer, unwrittenNodes);
		
		writer.writeDot();
		writer.writePng();
	}
	
	protected Set<String> writeEdges(GraphWriter writer) {

		Set<String> unwrittenNodes = reduceNodes(new HashSet<String>(), (accumulator, n) -> {
			
			accumulator.add(n.id().toKey());

			return accumulator;

		}).value();
		
		forEachEdge((from, edge, to) -> {

			unwrittenNodes.remove(from.id().toKey());
			unwrittenNodes.remove(to.id().toKey());

			writer.writeEdge(from, edge, to);
		});
		
		return unwrittenNodes;
	}

	protected void writeNodes(GraphWriter writer, Set<String> nodesToWrite) {
		for(String nodeKey : nodesToWrite) {

			Node<NV> node = findNode(nodeKey);

			if(node != null) {
				writer.writeNode(node);
			}
		}
	}
	
}
