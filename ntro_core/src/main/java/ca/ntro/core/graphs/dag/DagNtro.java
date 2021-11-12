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
import ca.ntro.core.identifyers.matchers.PathPattern;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.Result;
import ca.ntro.core.wrappers.ResultNtro;

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
	public void addEdge(N from, E edge, N to) throws CycleException {
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
	
	private void detectCycleFrom(N from) throws CycleException {

		Result<Void> result = foldEachReachableNode(from, null, (accumulator, n) -> {
			if(from == n) {
				throw new CycleException();
			}

			return null;
		});
		
		if(result.hasException()) {
			
			if(result.exception() instanceof CycleException) {
				
				throw (CycleException) result.exception();
				
			}else {
				
				Ntro.exceptionThrower().throwException(result.exception());
			}
		}
	}

	@Override
	public N findNode(NodeId nodeId) throws NodeNotFoundException {
		N node = nodes.get(nodeId.toKey());
		
		if(node == null) {
			
			throw new NodeNotFoundException("Node not found for " + nodeId);
		}

		return node;
	}

	@Override
	public N findNode(String rawNodeId) throws NodeNotFoundException {
		N node = null;
		
		if(!rawNodeId.contains(PathPattern.NAME_WILDCARD)
				&& !rawNodeId.contains(PathPattern.SUBPATH_WILDCARD)) {
			
			node = findNode(new NodeId(rawNodeId));
		}
		
		if(node == null) {

			node = findNode(new NodeMatcherNtro<N>(rawNodeId));
		}

		if(node == null) {
			throw new NodeNotFoundException("Node not found for " + rawNodeId);
		}

		return node;
	}

	@Override
	public N findNode(NodeMatcher<N> nodeMatcher) throws NodeNotFoundException {
		N node = null;

		for(N candidateNode : nodes.values()) {
			if(nodeMatcher.matches(node)) {
				node = candidateNode;
				break;
			}
		}

		if(node == null) {
			throw new NodeNotFoundException("Node not found for " + nodeMatcher);
		}

		return node;
	}

	@Override
	public void forEachNode(NodeVisitor<N> visitor) {
		foldEachNode(null, (accumulator, n) -> {

			visitor.visitNode(n);

			return null;
		});
	}

	@Override
	public <R> Result<R> foldEachNode(R initialValue, NodeFolder<N, R> folder) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		for(N node : nodes.values()) {
			try {
				
				result.registerValue(folder.foldNode(result.value(), node));

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
		foldEachEdge(null, (accumulator, from, edge, to) -> {

			visitor.visitEdge(from, edge, to);

			return null;
		});
	}

	@Override
	public <R> Result<R> foldEachEdge(R initialValue, EdgeFolder<N, E, R> folder) {

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
						
						result.registerValue(folder.foldEdge(result.value(), from, edge, to));

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
		foldEachReachableNode(from, null, (accumulator, n) -> {

			visitor.visitNode(n);

			return null;
		});
	}

	@Override
	public void forEachReachableNode(N from, List<Direction> directions, NodeVisitor<N> visitor) {
		foldEachReachableNode(from, directions, null, (accumulator, n) -> {

			visitor.visitNode(n);

			return null;
		});
	}

	@Override
	public <R> Result<R> foldEachReachableNode(N from, R initialValue, NodeFolder<N, R> folder) {
		List<Direction> directions = new ArrayList<>();
		directions.add(new ForwardNtro());

		return foldEachReachableNode(from, directions, initialValue, folder);
	}


	@Override
	public <R extends Object> Result<R> foldEachReachableNode(N from, 
			                                                  List<Direction> directions, 
			                                                  R initialValue, 
			                                                  NodeFolder<N, R> folder) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		foldEachReachableNode(new HashSet<String>(), from, directions, result, folder);
		
		return result;
	}

	private <R extends Object> void foldEachReachableNode(Set<String> visitedNodes, 
			                                              N from, 
			                                              List<Direction> directions, 
			                                              ResultNtro<R> accumulator,
			                                              NodeFolder<N,R> folder) {
		if(accumulator.hasException()) {
			return;
		}
		
		
		for(Direction direction : directions) {
			
			if(direction instanceof Forward) {

				foldEachReachableNodeInDirection(visitedNodes, from, directions, accumulator, folder, edgesForward);
				
			}else if(direction instanceof Backward) {
				
				foldEachReachableNodeInDirection(visitedNodes, from, directions, accumulator, folder, edgesBackward);
			}
		}
	}

	private <R extends Object> void foldEachReachableNodeInDirection(Set<String> visitedNodes, 
			                                                         N from, 
			                                                         List<Direction> directions,
			                                                         ResultNtro<R> accumulator,
			                                                         NodeFolder<N,R> folder,
			                                                         Map<String, Map<String, N>> edgesMap) {
		if(accumulator.hasException()) {
			return;
		}

		if(visitedNodes.contains(from.id().toKey())) {
			return;
		}

		visitedNodes.add(from.id().toKey());
		

		Map<String, N> edgesFrom = edgesMap.get(from.id().toKey());
		
		if(edgesFrom != null) {

			for(N to : edgesFrom.values()) {
				
				try {

					accumulator.registerValue(folder.foldNode(accumulator.value(), to));

				} catch(Break e) { 

					break; 

				} catch(Throwable t) {
					
					accumulator.registerException(t);
					break;
				}

				foldEachReachableNode(visitedNodes, to, directions, accumulator, folder);
			}
		}
	}

	@Override
	public void write(DagWriter<N, E> writer) {

		Set<String> unwrittenNodes = writeEdges(writer);

		writeNodes(unwrittenNodes, writer);
	}

	private Set<String> writeEdges(DagWriter<N, E> writer) {
		
		Set<String> unwrittenNodes = new HashSet<>(nodes.keySet());
		
		forEachEdge((from, edge, to) -> {

			unwrittenNodes.remove(from.id().toKey());
			unwrittenNodes.remove(to.id().toKey());

			writer.writeEdge(from, edge, to);
		});
		
		return unwrittenNodes;
	}

	private void writeNodes(Set<String> nodesToWrite, DagWriter<N, E> writer) {
		for(String nodeKey : nodesToWrite) {
			N node = nodes.get(nodeKey);
			if(node != null) {
				writer.writeNode(node);
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
