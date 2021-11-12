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
	
	private void detectCycleFrom(N from) throws CycleException {

		Result<Boolean> cycleDetected = foldEachReachableNode(from, false, (accumulator, n) -> {
			if(accumulator) {
				throw new Break();
			}

			if(n == from) {
				return true;
			}

			return accumulator;

		});

		if(cycleDetected.get()) {
			throw new CycleException();
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
	
	private N findNode(NodeId nodeId) throws NodeNotFoundException {
		N node = nodes.get(nodeId.toKey());
		
		if(node == null) {
			
			throw new NodeNotFoundException("Node not found for " + nodeId);
		}

		return node;
	}

	private N findNode(NodeMatcher<N> nodeMatcher) throws NodeNotFoundException {
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
	public void addEdge(NodeId fromId, E edge, NodeId toId) throws NodeNotFoundException, CycleException {
		N from = findNode(fromId);
		N to = findNode(toId);
		
		addEdge(from, edge, to);
	}

	@Override
	public void addEdge(NodeMatcher<N> fromMatcher, E edge, NodeMatcher<N> toMatcher) throws NodeNotFoundException, CycleException {
		N from = findNode(fromMatcher);
		N to = findNode(toMatcher);
		
		addEdge(from, edge, to);
	}

	@Override
	public void addEdge(String fromPattern, E edge, String toPattern) throws NodeNotFoundException, CycleException {
		N from = findNode(new NodeMatcherNtro<N>(fromPattern));
		N to = findNode(new NodeMatcherNtro<N>(toPattern));
		
		addEdge(from, edge, to);
	}

	@Override
	public <R> Result<R> foldEachNode(R initialValue, NodeFolder<N, R> folder) {
		
		Result<R> result = new ResultNtro<R>(initialValue);
		
		for(N node : nodes.values()) {
			try {

				result = new ResultNtro<R>(folder.foldNode(result.get(), node));

			} catch (Break e) { 

				break; 

			} catch (Throwable e) {
				
				result = ResultNtro.fromException(e);
				break;
			}
		}

		return result;
	}

	@Override
	public void forEachNode(NodeVisitor<N> visitor) {
		foldEachNode(null, (accumulator, n) -> {

			visitor.visitNode(n);

			return null;
		});
	}

	@Override
	public void forEachEdge(EdgeVisitor<N, E> visitor) {
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

						visitor.visitEdge(from, edge, to);

					} catch (Break e) { break; }
				}
			}
		}
	}

	@Override
	public void forEachReachableNode(N from, NodeVisitor<N> visitor) {
		List<Direction> directions = new ArrayList<>();
		directions.add(new ForwardNtro());

		forEachReachableNode(from, directions, visitor);
	}

	@Override
	public void forEachReachableNode(N from, List<Direction> directions, NodeVisitor<N> visitor) {
		forEachReachableNode(new HashSet<String>(), from, directions, visitor);
	}

	private void forEachReachableNode(Set<String> visitedNodes, N from, List<Direction> directions, NodeVisitor<N> visitor) {
		for(Direction direction : directions) {
			
			if(direction instanceof Forward) {

				forEachReachableNodeInDirection(visitedNodes, from, directions, visitor, edgesForward);
				
			}else if(direction instanceof Backward) {

				forEachReachableNodeInDirection(visitedNodes, from, directions, visitor, edgesBackward);
			}
		}
	}

	private void forEachReachableNodeInDirection(Set<String> visitedNodes, 
			                                     N from, 
			                                     List<Direction> directions,
			                                     NodeVisitor<N> visitor,
			                                     Map<String, Map<String, N>> edgesMap) {

		if(visitedNodes.contains(from.id().toKey())) {
			return;
		}

		Map<String, N> edgesFrom = edgesMap.get(from.id().toKey());
		
		if(edgesFrom != null) {

			for(N to : edgesFrom.values()) {
				
				try {

					visitor.visitNode(to);

				} catch(Break e) { break; }

				visitedNodes.add(to.id().toKey());
				
				forEachReachableNode(visitedNodes, to, directions, visitor);
			}
		}
	}

	@Override
	public void write(DagWriter<N, E> writer) {

		Set<String> remainingNodes = writeEdges(writer);

		writeNodes(remainingNodes, writer);
	}

	private Set<String> writeEdges(DagWriter<N, E> writer) {
		
		Set<String> nodesToWrite = nodes.keySet();
		
		forEachEdge((from, edge, to) -> {

			nodesToWrite.remove(from.id().toKey());
			nodesToWrite.remove(to.id().toKey());

			writer.writeEdge(from, edge, to);

		});
		
		return nodesToWrite;
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


	@Override
	public <R> Result<R> foldEachEdge(R initialValue, EdgeFolder<N, E, R> visitor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> foldEachReachableNode(N from, R initialValue, NodeFolder<N, R> folder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> foldEachReachableNode(N from, List<Direction> directions, R initialValue,
			NodeFolder<N, E> folder) {
		// TODO Auto-generated method stub
		return null;
	}

}
