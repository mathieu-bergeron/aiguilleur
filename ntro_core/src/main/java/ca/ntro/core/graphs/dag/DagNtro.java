package ca.ntro.core.graphs.dag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.dag.directions.Backward;
import ca.ntro.core.graphs.dag.directions.Direction;
import ca.ntro.core.graphs.dag.directions.Forward;
import ca.ntro.core.graphs.dag.directions.ForwardNtro;
import ca.ntro.core.graphs.dag.exceptions.CycleException;
import ca.ntro.core.graphs.dag.exceptions.NodeNotFoundException;
import ca.ntro.core.graphs.writers.DagWriter;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.path.PathPattern;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class DagNtro<N extends Node, E extends Edge> implements Dag<N,E> {
	
	private GraphId id;
	private Map<String, N> nodes = new HashMap<>();
	private Map<String, E> edges = new HashMap<>();
	private Map<String, Map<String, N>> edgesForward = new HashMap<>();
	private Map<String, Map<String, N>> edgesBackward = new HashMap<>();

	public DagNtro() {
		this.id = GraphId.newGraphId();
	}

	public DagNtro(String graphName) {
		this.id = GraphId.fromGraphName(graphName);
	}
	
	@Override
	public void addNode(N n) {
		nodes.put(n.id().toKey(), n);
	}

	private void addEdge(E e) {
		edges.put(e.id().toKey(), e);
	}
	
	@Override
	public void addEdge(N from, E edge, N to) {
		addNode(from);
		addNode(to);

		addEdge(edge);

		addToEdgesMap(edgesForward, from, edge, to);
		addToEdgesMap(edgesBackward, to, edge, from);
		
		detectCycleFrom(from);
	}

	private void addToEdgesMap(Map<String, Map<String, N>> edgesMap, N from, E edge, N to) {
		String fromKey = from.id().toKey();
		String edgeKey = edge.id().toKey();

		Map<String, N> edgesFrom = edgesMap.get(fromKey);

		if(edgesFrom == null) {
			edgesFrom = new HashMap<String, N>();
			edgesMap.put(fromKey, edgesFrom);
		}
		
		edgesFrom.put(edgeKey, to);
	}
	
	private void detectCycleFrom(N from) {

		Result<Void> result = reduceReachableNodes(from, null, (accumulator, n) -> {
			if(from == n) {
				throw new CycleException();
			}

			return null;
		});
		
		result.throwException();
	}

	@Override
	public N findNode(NodeId nodeId) {
		N node = nodes.get(nodeId.toKey());
		
		if(node == null) {

			Ntro.exceptionThrower().throwException(new NodeNotFoundException("Node not found for " + nodeId));
		}

		return node;
	}

	@Override
	public N findNode(String rawNodeId) {
		N node = null;
		
		if(!rawNodeId.contains(PathPattern.NAME_WILDCARD)
				&& !rawNodeId.contains(PathPattern.SUBPATH_WILDCARD)) {
			
			node = findNode(new NodeId(rawNodeId));
		}
		
		if(node == null) {

			node = findNode(new NodeMatcherNtro<N>(rawNodeId));
		}

		if(node == null) {

			Ntro.exceptionThrower().throwException(new NodeNotFoundException("Node not found for " + rawNodeId));
		}

		return node;
	}

	@Override
	public N findNode(NodeMatcher<N> nodeMatcher) {
		N node = null;

		for(N candidateNode : nodes.values()) {
			if(nodeMatcher.matches(node)) {
				node = candidateNode;
				break;
			}
		}

		if(node == null) {

			Ntro.exceptionThrower().throwException(new NodeNotFoundException("Node not found for " + nodeMatcher));
		}

		return node;
	}

	@Override
	public void forEachNode(NodeVisitor<N> visitor) {
		reduceNodes(null, (accumulator, n) -> {

			visitor.visitNode(n);

			return null;
		});
	}

	@Override
	public <R> Result<R> reduceNodes(R initialValue, NodeReducer<N, R> reduces) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		for(N node : nodes.values()) {
			try {
				
				result.registerValue(reduces.reduce(result.value(), node));

			} catch (Break e) { 

				break; 

			} catch (Throwable e) {
				
				result.registerException(e);
				break;
			}
		}

		return result;
	}

	@Override
	public void forEachEdge(EdgeVisitor<N, E> visitor) {
		recudeEdges(null, (accumulator, from, edge, to) -> {

			visitor.visitEdge(from, edge, to);

			return null;
		});
	}

	@Override
	public <R> Result<R> recudeEdges(R initialValue, EdgeReducer<N, E, R> reducer) {

		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		for(Map.Entry<String, Map<String, N>> edgesForwardFrom : edgesForward.entrySet()) {

			String fromKey = edgesForwardFrom.getKey();
			N from = nodes.get(fromKey);
			
			Map<String, N> edgesTo = edgesForwardFrom.getValue();
			
			for(Map.Entry<String, N> edgeTo : edgesTo.entrySet()) {
				
				String edgeKey = edgeTo.getKey();
				E edge = edges.get(edgeKey);

				N to = edgeTo.getValue();
				
				if(from != null && edge != null && to != null) {
					
					try {
						
						result.registerValue(reducer.reduce(result.value(), from, edge, to));

					} catch (Break e) { 

						break; 

					} catch (Throwable e) {
						
						result.registerException(e);
						break;

					}
				}
			}
		}

		return result;
	}

	@Override
	public void forEachReachableNode(N from, NodeVisitor<N> visitor) {
		forEachReachableNode(from, new Direction[] {new ForwardNtro()}, visitor);
	}

	@Override
	public void forEachReachableNode(N from, Direction[] directions, NodeVisitor<N> visitor) {
		forEachReachableNode(from, directions, SearchStrategy.BREADTH_FIRST_SEARCH, visitor);
	}

	@Override
	public void forEachReachableNode(N from, 
			                         Direction[] directions, 
			                         SearchStrategy searchStrategy, 
			                         NodeVisitor<N> visitor) {

		reduceReachableNodes(from, 
				             directions, 
				             searchStrategy,
				             null, 
				             (accumulator, n) -> {

			visitor.visitNode(n);

			return null;
		});

	}

	@Override
	public <R> Result<R> reduceReachableNodes(N from, R initialValue, NodeReducer<N, R> reducer) {

		return reduceReachableNodes(from, 
				                    new Direction[] {new ForwardNtro()}, 
				                    SearchStrategy.DEPTH_FIRST_SEARCH, 
				                    initialValue, 
				                    reducer);
	}

	@Override
	public <R extends Object> Result<R> reduceReachableNodes(N from, 
			                                                 Direction[] directions, 
			                                                 R initialValue, 
			                                                 NodeReducer<N, R> reducer) {

		return reduceReachableNodes(from, 
								    directions,
				                    SearchStrategy.BREADTH_FIRST_SEARCH, 
				                    initialValue, 
				                    reducer);
	}


	@Override
	public <R extends Object> Result<R> reduceReachableNodes(N from, 
			                                                 Direction[] directions, 
			                                                 SearchStrategy searchStrategy,
			                                                 R initialValue, 
			                                                 NodeReducer<N, R> reducer) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		if(searchStrategy == SearchStrategy.BREADTH_FIRST_SEARCH) {

			reduceReachableNodesBreadthFirst(new HashSet<String>(), from, directions, result, reducer);
			
		}else {

			reduceReachableNodesDepthFirst(new HashSet<String>(), from, directions, result, reducer);
		}
		
		return result;
	}


	private <R extends Object> void reduceReachableNodesBreadthFirst(Set<String> visitedNodes, 
			                                                         N from, 
			                                                         Direction[] directions, 
			                                                         ResultNtro<R> accumulator,
			                                                         NodeReducer<N,R> reducer) {
		if(accumulator.hasException()) {
			return;
		}
		
		List<String> nodesToVisit = new ArrayList<>();

		for(Direction direction : directions) {
			
			if(direction instanceof Forward) {

				nodesToVisit.addAll(reachableNodesOneStep(visitedNodes, from, edgesForward));
				
			}else if(direction instanceof Backward) {

				nodesToVisit.addAll(reachableNodesOneStep(visitedNodes, from, edgesBackward));
			}
		}
		
		for(String nodeKey : nodesToVisit) {
			
			N node = nodes.get(nodeKey);
			
			if(node != null) {

				try {
				
					accumulator.registerValue(reducer.reduce(accumulator.value(), node));
					
					reduceReachableNodesBreadthFirst(visitedNodes, node, directions, accumulator, reducer);

				} catch(Break e) {
					
					break;

				} catch(Throwable t) {
					
					accumulator.registerException(t);
					break;
				}
			}

			visitedNodes.add(node.id().toKey());
		}
	}

	private <R extends Object> Set<String> reachableNodesOneStep(Set<String> visitedNodes, 
			                                                      N from, 
			                                                      Map<String, Map<String, N>> edgesMap) {
		
		Set<String> nodesToVisit = new HashSet<>();

		Map<String, N> edgesFrom = edgesMap.get(from.id().toKey());
		
		if(edgesFrom != null) {
			
			for(N n : edgesFrom.values()) {
				
				if(!visitedNodes.contains(n.id().toKey())) {

					nodesToVisit.add(n.id().toKey());
				}
			}
		}
		
		return nodesToVisit;
	}

	private <R extends Object> void reduceReachableNodesDepthFirst(Set<String> visitedNodes, 
			                                                       N from, 
			                                                       Direction[] directions, 
			                                                       ResultNtro<R> accumulator,
			                                                       NodeReducer<N,R> reducer) {
		if(accumulator.hasException()) {
			return;
		}
		
		for(Direction direction : directions) {
			
			if(direction instanceof Forward) {

				reduceNodesInDirectionDepthFirst(visitedNodes, from, directions, accumulator, reducer, edgesForward);
				
			}else if(direction instanceof Backward) {
				
				reduceNodesInDirectionDepthFirst(visitedNodes, from, directions, accumulator, reducer, edgesBackward);
			}
		}
	}

	private <R extends Object> void reduceNodesInDirectionDepthFirst(Set<String> visitedNodes, 
			                                                         N from, 
			                                                         Direction[] directions,
			                                                         ResultNtro<R> accumulator,
			                                                         NodeReducer<N,R> reducer,
			                                                         Map<String, Map<String, N>> edgesMap) {
		if(accumulator.hasException()) {
			return;
		}

		Map<String, N> edgesFrom = edgesMap.get(from.id().toKey());
		
		if(edgesFrom != null) {

			for(N to : edgesFrom.values()) {
				
				if(!visitedNodes.contains(to.id().toKey())) {

					visitedNodes.add(to.id().toKey());

					try {

						accumulator.registerValue(reducer.reduce(accumulator.value(), to));

						reduceReachableNodesDepthFirst(visitedNodes, to, directions, accumulator, reducer);

					} catch(Break e) { 

						break; 

					} catch(Throwable t) {
						
						accumulator.registerException(t);
						break;
					}
				}
			}
		}
	}


	@Override
	public GraphId id() {
		return this.id;
	}

	@Override
	public String label() {
		return id().toString();
	}

}
