package ca.ntro.core.graphs.generic_graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeAlreadyAddedException;
import ca.ntro.core.graphs.EdgeId;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeAlreadyAddedException;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.ReachableEdgeReducer;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericGraphBuilderNtro<SO extends SearchOptions, NV extends NodeValue, EV extends EdgeValue, G extends GenericGraph<SO,NV,EV>> 
       extends        GenericGraphNtro<SO, NV, EV>
       implements     GenericGraphBuilder<SO,NV,EV,G>, GenericGraph<SO,NV,EV> {

	private GraphId id;

	private Set<String> rootNodes = new HashSet<>();
	private Map<String, Node<NV>> nodes = new HashMap<>();
	private Map<String, Edge<EV>> edges = new HashMap<>();

	// nodeKey -> edgeName -> edgeKey -> Node
	private Map<String, Map<String, Map<String, Node<NV>>>> edgesForward = new HashMap<>();

	protected GraphId getId() {
		return id;
	}

	protected void setId(GraphId id) {
		this.id = id;
	}

	protected Map<String,Node<NV>> getNodes() {
		return nodes;
	}

	protected void setNodes(Map<String,Node<NV>> nodes) {
		this.nodes = nodes;
	}

	protected Set<String> getRootNodes() {
		return rootNodes;
	}

	protected void setRootNodes(Set<String> rootNodes) {
		this.rootNodes = rootNodes;
	}

	protected Map<String,Edge<EV>> getEdges() {
		return edges;
	}

	protected void setEdges(Map<String,Edge<EV>> edges) {
		this.edges = edges;
	}

	protected Map<String, Map<String, Node<NV>>> getEdgesForward() {
		return edgesForward;
	}

	protected void setEdgesForward(Map<String,Map<String, Node<NV>>> edgesForward) {
		this.edgesForward = edgesForward;
	}

	public GenericGraphBuilderNtro() {
		setId(GraphId.newGraphId());
	}

	public GenericGraphBuilderNtro(String graphName) {
		setId(GraphId.fromGraphName(graphName));
	}

	@Override
	public Node<NV> addNode(NV nodeValue) {

		NodeId nodeId = new NodeId(nodeValue.name().toKey());

		Node<NV> node = new NodeNtro<>(nodeId, nodeValue);
		
		addNode(node);

		return node;
	}

	private void addNode(Node<NV> node) {
		if(getNodes().containsKey(node.id().toKey())) {

			Ntro.exceptionThrower().throwException(new NodeAlreadyAddedException("NodeId already taken: " + node.id().toKey()));

		}else {
			
			memorizeNode(node);
			addRootNode(node);
		}
	}

	private void memorizeNode(Node<NV> node) {
		if(!getNodes().containsKey(node.id().toKey())) {
			getNodes().put(node.id().toKey(), node);
		}
	}

	private void addRootNode(Node<NV> node) {
		getRootNodes().add(node.id().toKey());
	}

	private void removeRootNode(Node<NV> node) {
		getRootNodes().remove(node.id().toKey());
	}

	private void addEdge(Edge<EV> edge) {
		if(edges.containsKey(edge.id().toKey())) {
			Ntro.exceptionThrower().throwException(new EdgeAlreadyAddedException("EdgeId already taken: " + edge.id().toKey()));
		}
		
		edges.put(edge.id().toKey(), edge);
	}

	@Override
	public Edge<EV> addEdge(Node<NV> from, EV edgeValue, Node<NV> to) {
		memorizeNode(from);
		memorizeNode(to);
		
		addRootNode(from);
		removeRootNode(to);
		
		EdgeId edgeId = newEdgeId(from, edgeValue, to);
		
		Edge<EV> edge = new EdgeNtro<EV>(edgeId, edgeValue);
		
		addEdge(edge);

		addToEdgesMaps(from, edge, to);
		
		detectCycleFrom(from);
		
		return edge;
	}

	protected abstract EdgeId newEdgeId(Node<NV> from, EV edgeValue, Node<NV> to);

	protected abstract void addToEdgesMaps(Node<NV> from, Edge<EV> edge, Node<NV> to);

	protected abstract void detectCycleFrom(Node<NV> from);

	protected abstract Map<String, Map<String, Node<NV>>> edgesMapForDirection(Direction direction);

	protected void addToEdgesMap(Map<String, Map<String, Node<NV>>> edgesMap, Node<NV> from, Edge<EV> edge, Node<NV> to) {
		String fromKey = from.id().toKey();
		String edgeKey = edge.id().toKey();

		Map<String, Node<NV>> edgesFrom = edgesMap.get(fromKey);

		if(edgesFrom == null) {
			edgesFrom = new HashMap<String, Node<NV>>();
			edgesMap.put(fromKey, edgesFrom);
		}
		
		edgesFrom.put(edgeKey, to);
	}

	@Override
	protected <R> void _reduceRootNodes(ResultNtro<R> result, NodeReducer<NV, R> reducer) {
		if(result.hasException()) {
			return;
		}

		for(String rootNodeKey : getRootNodes()) {
			
			Node<NV> node = getNodes().get(rootNodeKey);

			try {

				result.registerValue(reducer.reduceNode(result.value(), node));

			} catch (Throwable e) {

				result.registerException(e);
				break;
			}
		}
	}

	@Override
	protected <R> void _reduceNextEdgeNames(Node<NV> fromNode, 
			                                Direction direction, 
			                                ResultNtro<R> result, 
			                                EdgeNameReducer<R> reducer) {

		if(result.hasException()) {
			return;
		}
		
		Map<String, Map<String, Node<NV>>> edgesMap = edgesMapForDirection(direction);
		
		if(edgesMap != null) {
			
			Map<String, Node<NV>> edgesFromNode = edgesMap.get(fromNode.id().toKey());
			
			if(edgesFromNode != null) {
				
				for(String edgeName : edgesFromNode.keySet()) {
					
					try {

						result.registerValue(reducer.reduceEdgeName(result.value(), edgeName));

					} catch (Throwable e) {
						
						result.registerException(e);
						break;
					}
				}
			}
		}
	}

	@Override
	protected <R> void _reduceNextEdgesByName(Node<NV> fromNode, 
			                                  Direction direction, 
			                                  String edgeName, 
			                                  ResultNtro<R> result, 
			                                  ReachableEdgeReducer<NV, EV, R> reducer) {

		
	}
	
	
	
	
	/*

	

	

	@Override
	public Node<NV> findNode(NodeId nodeId) {
		
		Node<NV> node = nodes.get(nodeId.toKey());
		
		if(node == null) {

			Ntro.exceptionThrower().throwException(new NodeNotFoundException("Node not found for " + nodeId));
		}

		return node;
	}

	@Override
	public Node<NV> findNode(String rawNodeId) {
		Node<NV> node = null;
		
		if(!rawNodeId.contains(PathPattern.NAME_WILDCARD)
				&& !rawNodeId.contains(PathPattern.SUBPATH_WILDCARD)) {
			
			node = findNode(new NodeId(rawNodeId));
		}
		
		if(node == null) {

			Ntro.exceptionThrower().throwException(new NodeNotFoundException("Node not found for " + rawNodeId));
		}

		return node;
	}

	@Override
	public Node<NV> findNode(NodeMatcher<NV> nodeMatcher) {
		
		Result<Node<NV>> result = reduceNodes(null, (accumulator, n) -> {

			if(nodeMatcher.matches(n.value())) {
				
				accumulator = n;
			}
			
			return accumulator;
			
		});
		
		if(!result.hasValue()) {

			Ntro.exceptionThrower().throwException(new NodeNotFoundException("Node not found for " + nodeMatcher));
		}
		
		return result.value();
	}

	@Override
	public Node<NV> findNode(NV nodeValue) {
		return findNode(new NodeMatcherByValue<NV>(nodeValue));
	}

	@Override
	public void forEachNode(NodeVisitor<NV> visitor) {
		reduceNodes(null, (accumulator, n) -> {

			visitor.visitNode(n);

			return null;
		});
	}


	@Override
	public <R> Result<R> reduceNodes(R initialValue, NodeReducer<NV, R> reduces) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		for(Node<NV> node : nodes.values()) {
			try {
				
				result.registerValue(reduces.reduceNode(result.value(), node));

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
	public void forEachEdge(EdgeVisitor<NV,EV> visitor) {
		reduceEdges(null, (accumulator, from, edge, to) -> {

			visitor.visitEdge(from, edge, to);

			return null;
		});
	}

	@Override
	public <R> Result<R> reduceEdges(R initialValue, EdgeReducer<NV,EV,R> reducer) {

		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		for(Map.Entry<String, Map<String, Node<NV>>> edgesForwardFrom : getEdgesForward().entrySet()) {

			String fromKey = edgesForwardFrom.getKey();
			Node<NV> from = findNode(fromKey);
			
			Map<String, Node<NV>> edgesTo = edgesForwardFrom.getValue();
			
			for(Map.Entry<String, Node<NV>> edgeTo : edgesTo.entrySet()) {
				
				String edgeKey = edgeTo.getKey();
				Edge<EV> edge = edges.get(edgeKey);

				Node<NV> to = edgeTo.getValue();
				
				if(from != null && edge != null && to != null) {
					
					try {
						
						result.registerValue(reducer.reduceEdge(result.value(), from, edge, to));

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
	public void forEachReachableNode(Node<NV> from, ReachableNodeVisitor<NV,EV> visitor) {
		forEachReachableNode(from, defaultSearchOptions(), visitor);
	}
	
	protected abstract SO defaultSearchOptions();

	@Override
	public void forEachReachableNode(Node<NV> from, SO options, ReachableNodeVisitor<NV,EV> visitor) {
		reduceReachableNodes(from, options, (accumulator, distance, n) -> {
			visitor.visitReachableNode(distance, n);

			return null;
		});
	}

	@Override
	public <R> Result<R> reduceReachableNodes(Node<NV> from, 
			                                  R initialValue, 
			                                  ReachableNodeReducer<NV,EV,R> reducer) {

		return reduceReachableNodes(from, 
									defaultSearchOptions(),
				                    initialValue, 
				                    reducer);
	}

	@Override
	public <R> Result<R> reduceReachableNodes(Node<NV> from, 
			                                  SO options, 
			                                  R initialValue, 
			                                  ReachableNodeReducer<NV,EV,R> reducer) {

		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		if(options.searchStrategy() == SearchStrategy.BREADTH_FIRST_SEARCH) {

			reduceReachableNodesBreadthFirst(new HashSet<String>(), from, options, result, 0, reducer);
			
		}else {

			reduceReachableNodesDepthFirst(new HashSet<String>(), from, options, result, 0, reducer);
		}
		
		
		return result;
	}


	private <R extends Object> void reduceReachableNodesBreadthFirst(Set<String> visitedNodes, 
			                                                         Node<NV> from, 
			                                                         SO searchOptions,
			                                                         ResultNtro<R> accumulator,
			                                                         int distance,
			                                                         ReachableNodeReducer<NV,EV,R> reducer) {
		if(accumulator.hasException()) {
			return;
		}

		if(searchOptions.maxDistance().hasValue() 
				&& distance > searchOptions.maxDistance().value()) {
			return;
		}
		
		List<String> nodesToVisit = new ArrayList<>();

		for(Direction direction : searchOptions.directions()) {

			nodesToVisit.addAll(reachableNodesOneStep(visitedNodes, from, direction));
		}
		
		for(String nodeKey : nodesToVisit) {
			
			Node<NV> node = findNode(nodeKey);

			if(node != null) {

				try {
				
					accumulator.registerValue(reducer.reduceReachableNode(accumulator.value(), distance+1, node));
					
					reduceReachableNodesBreadthFirst(visitedNodes, node, searchOptions, accumulator, distance+1, reducer);

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

	protected <R extends Object> Set<String> reachableNodesOneStep(Set<String> visitedNodes, 
			                                                       Node<NV> from, 
			                                                       Direction direction) {

			Set<String> result = new HashSet<>();

			Map<String, Map<String, Node<NV>>> edgesMap = edgesMapForDirection(direction);
				
			if(edgesMap != null) {

				result = reachableNodesOneStep(visitedNodes, from, edgesMap);

			}
			
			return result;
	}

	protected <R extends Object> Set<String> reachableNodesOneStep(Set<String> visitedNodes, 
			                                                       Node<NV> from, 
			                                                       Map<String, Map<String, Node<NV>>> edgesMap) {
		
		Set<String> nodesToVisit = new HashSet<>();

		Map<String, Node<NV>> edgesFrom = edgesMap.get(from.id().toKey());
		
		if(edgesFrom != null) {
			
			for(Node<NV> n : edgesFrom.values()) {
				
				if(!visitedNodes.contains(n.id().toKey())) {

					nodesToVisit.add(n.id().toKey());
				}
			}
		}
		
		return nodesToVisit;
	}

	private <R extends Object> void reduceReachableNodesDepthFirst(Set<String> visitedNodes, 
			                                                       Node<NV> from, 
			                                                       SO searchOptions,
			                                                       ResultNtro<R> result,
			                                                       int distance,
			                                                       ReachableNodeReducer<NV,EV,R> reducer) {
		if(result.hasException()) {
			return;
		}

		if(searchOptions.maxDistance().hasValue() 
				&& distance > searchOptions.maxDistance().value()) {
			return;
		}

		for(Direction direction : searchOptions.directions()) {

			reduceNodesInDirectionDepthFirst(visitedNodes, 
					                         from, 
					                         searchOptions, 
					                         result, 
					                         distance, 
					                         reducer, 
					                         direction);
		}
	}

	protected <R extends Object> void reduceNodesInDirectionDepthFirst(Set<String> visitedNodes, 
			                                                           Node<NV> from, 
			                                                           SO searchOptions,
			                                                           ResultNtro<R> result,
			                                                           int distance,
			                                                           ReachableNodeReducer<NV,EV,R> reducer,
			                                                           Direction direction) {
		
		Map<String, Map<String, Node<NV>>> edgesMap = edgesMapForDirection(direction);
		
		if(edgesMap != null) {

			reduceNodesInDirectionDepthFirst(visitedNodes, 
					                         from, 
					                         searchOptions, 
					                         result, 
					                         distance, 
					                         reducer, 
					                         edgesMap);
			
		}
	}

	protected <R extends Object> void reduceNodesInDirectionDepthFirst(Set<String> visitedNodes, 
			                                                         Node<NV> from, 
			                                                         SO searchOptions,
			                                                         ResultNtro<R> result,
			                                                         int distance,
			                                                         ReachableNodeReducer<NV,EV,R> reducer,
			                                                         Map<String, Map<String, Node<NV>>> edgesMap) {
		if(result.hasException()) {
			return;
		}

		if(searchOptions.maxDistance().hasValue() 
				&& distance > searchOptions.maxDistance().value()) {
			return;
		}

		Map<String, Node<NV>> edgesFrom = edgesMap.get(from.id().toKey());
		
		if(edgesFrom != null) {

			for(Node<NV> to : edgesFrom.values()) {
				
				if(!visitedNodes.contains(to.id().toKey())) {

					visitedNodes.add(to.id().toKey());

					try {

						result.registerValue(reducer.reduceReachableNode(result.value(), distance+1, to));

						reduceReachableNodesDepthFirst(visitedNodes, to, searchOptions, result, distance+1, reducer);

					} catch(Break e) { 

						break; 

					} catch(Throwable t) {
						
						result.registerException(t);
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
	
	

	@Override
	public void forEachReachableEdge(Node<NV> fromNode, 
			                         ReachableEdgeVisitor<NV,EV> visitor) {
		
		forEachReachableEdge(fromNode, defaultSearchOptions(), visitor);
	}

	@Override
	public void forEachReachableEdge(Node<NV> fromNode, 
			                         SO searchOptions, 
			                         ReachableEdgeVisitor<NV,EV> visitor) {

		reduceReachableEdges(fromNode, searchOptions, null, (accumulator, walkedEdges, from, edge, to) -> {

			visitor.visitReachableEdge(walkedEdges, from, edge, to);

			return null;
		});
	}

	@Override
	public <R> Result<R> reduceReachableEdges(Node<NV> fromNode, 
			                                  R initialValue, 
			                                  ReachableEdgeReducer<NV,EV,R> reducer) {

		return reduceReachableEdges(fromNode, defaultSearchOptions(), initialValue, reducer);
	}

	@Override
	public <R> Result<R> reduceReachableEdges(Node<NV> fromNode, 
			                                  SO searchOptions, 
			                                  R initialValue, 
			                                  ReachableEdgeReducer<NV,EV,R> reducer) {
		
		return reduceReachableNodes(fromNode, searchOptions, initialValue, (accumulator, distance, n) ->{
			
			accumulator = reduceNextEdges(fromNode, searchOptions, accumulator, (innerAccumulator, innerDistance, from, edge, to) -> {
				
				return reducer.reduceReachableEdge(innerAccumulator,distance+1, from, edge, to);
				
			}).value();

			return accumulator;
		});
	}
	
	
	@Override
	public <R> Result<R> reduceNextEdges(Node<NV> fromNode, 
			                                SO searchOptions, 
			                                R initialValue,
			                                ReachableEdgeReducer<NV, EV, R> reducer) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		for(Direction direction : searchOptions.directions()) {
			
			Map<String, Map<String, Node<NV>>> edgesMap = edgesMapForDirection(direction);
			
			if(edgesMap != null) {

					reduceNextEdges(fromNode, 
									result,
			                        reducer,
			                        edgesMap);
				
			}
		}
		
		return result;
	}
	
	protected <R> void reduceNextEdges(Node<NV> fromNode, 
									   ResultNtro<R> result,
			                           ReachableEdgeReducer<NV,EV,R> reducer,
								       Map<String, Map<String, Node<NV>>> edgesMap){

		Map<String, Node<NV>> edgesFrom = edgesMap.get(fromNode.id().toKey());
		
		if(edgesFrom != null) {

			for(Map.Entry<String, Node<NV>> entry : edgesFrom.entrySet()) {
				
				String edgeKey = entry.getKey();
				Node<NV> to = entry.getValue();
				
				Edge<EV> edge = getEdges().get(edgeKey);
				
				List<Edge<EV>> walkedEdges = new ArrayList<>();
				walkedEdges.add(edge);
				
				try {

					result.registerValue(reducer.reduceReachableEdge(result.value(), walkedEdges, fromNode, edge, to));

				} catch(Break e) { 

					break; 

				} catch(Throwable t) {
					
					result.registerException(t);
					break;
				}
			}
		}
	}


	@Override
	public void visitEdgeWalk(Node<NV> from, EdgeWalk edgeWalk, EdgeWalkVisitor<NV,EV> visitor) {
		
		visitEdgeWalk(from, Direction.FORWARD, edgeWalk, visitor);
	}

	@Override
	public void visitEdgeWalk(Node<NV> from, Direction direction, EdgeWalk edgeWalk, EdgeWalkVisitor<NV, EV> visitor) {

		reduceEdgeWalk(from, direction, edgeWalk, null, (accumulator, walkedEdges, remainingEdgeWalk, n) -> {
			
			visitor.visitEdgeWalk(walkedEdges, remainingEdgeWalk, n);
			
			return null;
		});
	}

	@Override
	public <R> Result<R> reduceEdgeWalk(Node<NV> fromNode, EdgeWalk edgeWalk, R initialValue, EdgeWalkReducer<NV,EV,R> reducer) {
		return reduceEdgeWalk(fromNode, Direction.FORWARD, edgeWalk, initialValue, reducer);
	}

	@Override
	public <R> Result<R> reduceEdgeWalk(Node<NV> fromNode, Direction direction, EdgeWalk edgeWalk, R initialValue, EdgeWalkReducer<NV,EV,R> reducer) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		Map<String, Map<String, Node<NV>>> edgesMap = edgesMapForDirection(direction);
		
		if(edgesMap != null) {
			
			reduceEdgeWalk(fromNode, new ArrayList<Edge<EV>>(), edgeWalk, result, reducer, edgesMap);
		}

		return result;
	}

	protected <R> void reduceEdgeWalk(Node<NV> fromNode, 
			                          List<Edge<EV>> walkedEdges, 
			                          EdgeWalk remainingEdgeWalk, 
			                          ResultNtro<R> result,
			                          EdgeWalkReducer<NV,EV,R> reducer,
			                          Map<String, Map<String, Node<NV>>> edgesMap) {
		
		
		reduceNextEdges(fromNode, result, (accumulator, innerWalkedEdges, from, edge, to) -> {
			
			if(edge.id().isPrefisOfEdgeWalk(remainingEdgeWalk)) {
				
				List<Edge<EV>> newWalkedEdges = new ArrayList<>(innerWalkedEdges);
				newWalkedEdges.add(edge);
				
				EdgeWalk newRemainingEdgeWalk = remainingEdgeWalk.subPath(1);
				
				result.registerValue(reducer.reduceEdgeWalk(result.value(), innerWalkedEdges, newRemainingEdgeWalk, from));
				
				reduceEdgeWalk(to, newWalkedEdges, newRemainingEdgeWalk, result, reducer, edgesMap);

			}
			
			return result.value();
			
		}, edgesMap);
	}

	
	*/
	
	@SuppressWarnings("unchecked")
	@Override
	public G toGraph() {
		return (G) this;
	}

}