package ca.ntro.core.graphs.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.directions.Direction;
import ca.ntro.core.graphs.directions.Forward;
import ca.ntro.core.graphs.directions.ForwardNtro;
import ca.ntro.core.graphs.graph.Edge;
import ca.ntro.core.graphs.graph.EdgeReducer;
import ca.ntro.core.graphs.graph.EdgeVisitor;
import ca.ntro.core.graphs.graph.Node;
import ca.ntro.core.graphs.graph.NodeId;
import ca.ntro.core.graphs.graph.NodeMatcher;
import ca.ntro.core.graphs.graph.NodeMatcherNtro;
import ca.ntro.core.graphs.graph.NodeNotFoundException;
import ca.ntro.core.graphs.graph.NodeReducer;
import ca.ntro.core.graphs.graph.NodeVisitor;
import ca.ntro.core.graphs.graph.SearchStrategy;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.path.PathPattern;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class GraphNtro<N extends Node, E extends Edge> implements Graph<N,E> {
	

	private GraphId id;
	private Map<String, N> nodes = new HashMap<>();
	private Map<String, E> edges = new HashMap<>();
	private Map<String, Map<String, N>> edgesForward = new HashMap<>();

	protected GraphId getId() {
		return id;
	}

	protected void setId(GraphId id) {
		this.id = id;
	}

	protected Map<String, N> getNodes() {
		return nodes;
	}

	protected void setNodes(Map<String, N> nodes) {
		this.nodes = nodes;
	}

	protected Map<String, E> getEdges() {
		return edges;
	}

	protected void setEdges(Map<String, E> edges) {
		this.edges = edges;
	}

	protected Map<String, Map<String, N>> getEdgesForward() {
		return edgesForward;
	}

	protected void setEdgesForward(Map<String, Map<String, N>> edgesForward) {
		this.edgesForward = edgesForward;
	}

	public GraphNtro() {
		setId(GraphId.newGraphId());
	}

	public GraphNtro(String graphName) {
		setId(GraphId.fromGraphName(graphName));
	}
	
	@Override
	public void addNode(N n) {
		getNodes().put(n.id().toKey(), n);
	}

	private void addEdge(E e) {
		getEdges().put(e.id().toKey(), e);
	}
	
	@Override
	public void addEdge(N from, E edge, N to) {
		addNode(from);
		addNode(to);

		addEdge(edge);
		
		addToEdgesMaps(from, edge, to);
		
		detectCycleFrom(from);
	}

	protected Direction[] defaultDirections() {
		return new Direction[] {new ForwardNtro()};
	}

	protected SearchStrategy defaultStrategy() {
		return SearchStrategy.BREADTH_FIRST_SEARCH;
	}

	protected void addToEdgesMaps(N from, E edge, N to) {
		if(from.id().toKey().compareTo(to.id().toKey()) < 0) {

			addToEdgesMap(getEdgesForward(), from, edge, to);
			
		}else {

			addToEdgesMap(getEdgesForward(), to, edge, from);
		}
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
	
	protected void detectCycleFrom(N from) {
	}

	@Override
	public N findNode(NodeId nodeId) {
		N node = getNodes().get(nodeId.toKey());
		
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

		for(N candidateNode : getNodes().values()) {
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
		
		for(N node : getNodes().values()) {
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
		
		for(Map.Entry<String, Map<String, N>> edgesForwardFrom : getEdgesForward().entrySet()) {

			String fromKey = edgesForwardFrom.getKey();
			N from = getNodes().get(fromKey);
			
			Map<String, N> edgesTo = edgesForwardFrom.getValue();
			
			for(Map.Entry<String, N> edgeTo : edgesTo.entrySet()) {
				
				String edgeKey = edgeTo.getKey();
				E edge = getEdges().get(edgeKey);

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
		forEachReachableNode(from, defaultDirections(), defaultStrategy(), visitor);
	}

	@Override
	public void forEachReachableNode(N from, SearchStrategy searchStrategy, NodeVisitor<N> visitor) {
		forEachReachableNode(from, defaultDirections(), defaultStrategy(), visitor);
	}

	protected void forEachReachableNode(N from, 
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
	public <R> Result<R> reduceReachableNodes(N from, 
			                                  SearchStrategy searchStrategy, 
			                                  R initialValue, 
			                                  NodeReducer<N, R> reducer) {

		return reduceReachableNodes(from, 
									defaultDirections(),
				                    searchStrategy, 
				                    initialValue, 
				                    reducer);
	}

	@Override
	public <R> Result<R> reduceReachableNodes(N from, R initialValue, NodeReducer<N, R> reducer) {

		return reduceReachableNodes(from, 
									defaultDirections(),
									defaultStrategy(),
				                    initialValue, 
				                    reducer);
	}

	protected <R extends Object> Result<R> reduceReachableNodes(N from, 
			                                                 Direction[] directions, 
			                                                 R initialValue, 
			                                                 NodeReducer<N, R> reducer) {

		return reduceReachableNodes(from, 
								    directions,
								    defaultStrategy(),
				                    initialValue, 
				                    reducer);
	}


	protected <R extends Object> Result<R> reduceReachableNodes(N from, 
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

			nodesToVisit.addAll(reachableNodesOneStep(visitedNodes, from, direction));
		}
		
		for(String nodeKey : nodesToVisit) {
			
			N node = getNodes().get(nodeKey);
			
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
			                                                      Direction direction) {
		
			Set<String> result = new HashSet<>();

			if(direction instanceof Forward) {

				result = reachableNodesOneStep(visitedNodes, from, getEdgesForward());
			}
			
			return result;
	}

	protected <R extends Object> Set<String> reachableNodesOneStep(Set<String> visitedNodes, 
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
			reduceNodesInDirectionDepthFirst(visitedNodes, from, directions, accumulator, reducer, direction);
		}
	}

	protected <R extends Object> void reduceNodesInDirectionDepthFirst(Set<String> visitedNodes, 
			                                                           N from, 
			                                                           Direction[] directions,
			                                                           ResultNtro<R> accumulator,
			                                                           NodeReducer<N,R> reducer,
			                                                           Direction direction) {
		
		if(direction instanceof Forward) {

			reduceNodesInDirectionDepthFirst(visitedNodes, from, directions, accumulator, reducer, getEdgesForward());
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
		return getId();
	}

	@Override
	public String label() {
		return id().toString();
	}
}
