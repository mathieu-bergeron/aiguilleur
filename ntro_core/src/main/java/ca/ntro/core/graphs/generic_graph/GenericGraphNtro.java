package ca.ntro.core.graphs.generic_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeAlreadyAddedException;
import ca.ntro.core.graphs.EdgeId;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.EdgeVisitor;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeAlreadyAddedException;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeMatcher;
import ca.ntro.core.graphs.NodeNotFoundException;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.NodeVisitor;
import ca.ntro.core.graphs.ReachableEdgeReducer;
import ca.ntro.core.graphs.ReachableEdgeVisitor;
import ca.ntro.core.graphs.ReachableNodeReducer;
import ca.ntro.core.graphs.ReachableNodeVisitor;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.SearchStrategy;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.path.EdgeWalk;
import ca.ntro.core.path.Path;
import ca.ntro.core.path.PathPattern;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericGraphNtro<SO extends SearchOptions, NV extends NodeValue, EV extends EdgeValue, G extends GenericGraph<SO,NV,EV>> 
       implements     GenericGraphBuilder<SO,NV,EV,G>, GenericGraph<SO,NV,EV> {

	private GraphId id;

	private Map<String,Node<NV>> nodes = new HashMap<>();
	private Map<String, Edge<EV>> edges = new HashMap<>();
	private Map<String, Map<String, Node<NV>>> edgesForward = new HashMap<>();

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

	public GenericGraphNtro() {
		setId(GraphId.newGraphId());
	}

	public GenericGraphNtro(String graphName) {
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
		}
		
		getNodes().put(node.id().toKey(), node);
	}

	private void memorizeNode(Node<NV> node) {
		if(!getNodes().containsKey(node.id().toKey())) {
			getNodes().put(node.id().toKey(), node);
		}
	}

	private Edge<EV> addEdge(NodeId fromId, EV edgeValue, NodeId toId) {
		
		Path entityPath = Path.emptyPath();
		entityPath.append(Path.fromPath(fromId.toFilepath()));
		entityPath.append(Path.fromSingleName(edgeValue.name().toKey()));
		entityPath.append(Path.fromPath(toId.toFilepath()));

		EdgeId edgeId =  new EdgeId(entityPath);
		
		Edge<EV> edge = new EdgeNtro<>(edgeId, edgeValue);

		addEdge(edge);

		return edge;
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
		
		Edge<EV> edge = addEdge(from.id(), edgeValue, to.id());

		addToEdgesMaps(from, edge, to);
		
		detectCycleFrom(from);
		
		return edge;
	}

	protected void addToEdgesMaps(Node<NV> from, Edge<EV> edge, Node<NV> to) {
		if(from.id().toKey().compareTo(to.id().toKey()) < 0) {

			addToEdgesMap(getEdgesForward(), from, edge, to);
			
		}else {

			addToEdgesMap(getEdgesForward(), to, edge, from);
		}
	}

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
	
	protected abstract void detectCycleFrom(Node<NV> from);

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
		return findNode(new NodeMatcherNtro<NV>(nodeValue));
	}

	@Override
	public void forEachNode(NodeVisitor<Node<NV>> visitor) {
		reduceNodes(null, (accumulator, n) -> {

			visitor.visitNode(n);

			return null;
		});
	}


	@Override
	public <R> Result<R> reduceNodes(R initialValue, NodeReducer<Node<NV>, R> reduces) {
		
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
	public void forEachEdge(EdgeVisitor<Node<NV>, Edge<EV>> visitor) {
		recudeEdges(null, (accumulator, from, edge, to) -> {

			visitor.visitEdge(from, edge, to);

			return null;
		});
	}

	@Override
	public <R> Result<R> recudeEdges(R initialValue, EdgeReducer<Node<NV>, Edge<EV>, R> reducer) {

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
	public void forEachReachableNode(Node<NV> from, ReachableNodeVisitor<Node<NV>> visitor) {
		forEachReachableNode(from, defaultSearchOptions(), visitor);
	}
	
	protected abstract SO defaultSearchOptions();

	@Override
	public void forEachReachableNode(Node<NV> from, SO options, ReachableNodeVisitor<Node<NV>> visitor) {
		reduceReachableNodes(from, options, (accumulator, distance, n) -> {
			visitor.visitReachableNode(distance, n);

			return null;
		});
	}

	@Override
	public <R> Result<R> reduceReachableNodes(Node<NV> from, 
			                                  R initialValue, 
			                                  ReachableNodeReducer<Node<NV>, R> reducer) {

		return reduceReachableNodes(from, 
									defaultSearchOptions(),
				                    initialValue, 
				                    reducer);
	}

	@Override
	public <R> Result<R> reduceReachableNodes(Node<NV> from, 
			                                  SO options, 
			                                  R initialValue, 
			                                  ReachableNodeReducer<Node<NV>, R> reducer) {

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
			                                                         ReachableNodeReducer<Node<NV>,R> reducer) {
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

			if(direction == Direction.FORWARD) {

				result = reachableNodesOneStep(visitedNodes, from, getEdgesForward());
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
			                                                       ResultNtro<R> accumulator,
			                                                       int distance,
			                                                       ReachableNodeReducer<Node<NV>,R> reducer) {
		if(accumulator.hasException()) {
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
					                         accumulator, 
					                         distance, 
					                         reducer, 
					                         direction);
		}
	}

	protected <R extends Object> void reduceNodesInDirectionDepthFirst(Set<String> visitedNodes, 
			                                                           Node<NV> from, 
			                                                           SO searchOptions,
			                                                           ResultNtro<R> accumulator,
			                                                           int distance,
			                                                           ReachableNodeReducer<Node<NV>,R> reducer,
			                                                           Direction direction) {
		
		if(direction == Direction.FORWARD) {

			reduceNodesInDirectionDepthFirst(visitedNodes, 
					                         from, 
					                         searchOptions, 
					                         accumulator, 
					                         distance, 
					                         reducer, 
					                         getEdgesForward());
		}
	}

	private <R extends Object> void reduceNodesInDirectionDepthFirst(Set<String> visitedNodes, 
			                                                         Node<NV> from, 
			                                                         SO searchOptions,
			                                                         ResultNtro<R> accumulator,
			                                                         int distance,
			                                                         ReachableNodeReducer<Node<NV>,R> reducer,
			                                                         Map<String, Map<String, Node<NV>>> edgesMap) {
		if(accumulator.hasException()) {
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

						accumulator.registerValue(reducer.reduceReachableNode(accumulator.value(), distance+1, to));

						reduceReachableNodesDepthFirst(visitedNodes, to, searchOptions, accumulator, distance+1, reducer);

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
	
	

	@Override
	public void forEachReachableEdge(Node<NV> from, 
			                         ReachableEdgeVisitor<Node<NV>, Edge<EV>> visitor) {

		
	}

	@Override
	public void forEachReachableEdge(Node<NV> from, 
			                         SO searchOptions, 
			                         ReachableEdgeVisitor<Node<NV>, Edge<EV>> visitor) {
		
	}

	@Override
	public <R> Result<R> reduceReachableEdges(Node<NV> from, 
			                                  R initialValue, 
			                                  ReachableEdgeReducer<Node<NV>, Edge<EV>, R> reducer) {

		return null;
	}

	@Override
	public <R> Result<R> reduceReachableEdges(Node<NV> from, 
			                                  SO searchOptions, 
			                                  R initialValue, 
			                                  ReachableEdgeReducer<Node<NV>, Edge<EV>, R> reducer) {

		return null;
	}
	
	


	@Override
	public Node<NV> walkToNode(Node<NV> from, String rawEdgeWalk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<NV> walkToNode(Node<NV> from, EdgeId[] edgeWalk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<NV> walkToNode(Node<NV> from, EdgeWalk edgeWalk) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public G toGraph() {
		return (G) this;
	}
}
