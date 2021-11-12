package ca.ntro.core.graphs.dag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.dag.directions.Direction;
import ca.ntro.core.graphs.dag.exceptions.CycleException;
import ca.ntro.core.graphs.dag.exceptions.NodeNotFoundException;

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
		addEdge(edgesForward, from, edge, to);
		addEdge(edgesBackward, to, edge, from);
	}
	
	private void addEdge(Map<String, Map<String, N>> multimap, N from, E edge, N to) {
		String fromKey = from.id().toKey();
		String edgeKey = edge.id().toKey();

		Map<String, N> edgesFrom = multimap.get(fromKey);

		if(edgesFrom == null) {
			edgesFrom = new HashMap<String, N>();
			multimap.put(fromKey, edgesFrom);
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
	public void forEachNode(NodeVisitor<N> visitor) {
		
	}

	@Override
	public void forEachEdge(EdgeVisitor<N, E> visistor) {
		
	}

	@Override
	public void forEachReachableNode(N from, List<Direction> directions) {

	}

	@Override
	public void write(DagWriter<N, E> writer) {

		Set<String> remainingNodes = writeEdges(writer);

		writeNodes(remainingNodes, writer);
	}

	private Set<String> writeEdges(DagWriter<N, E> writer) {
		
		Set<String> nodesToWrite = nodes.keySet();

		for(Map.Entry<String, Map<String, N>> edgesFrom : edgesForward.entrySet()) {

			String fromKey = edgesFrom.getKey();
			Map<String, N> reachableNodes = edgesFrom.getValue();

				N from = nodes.get(fromKey);
				
				for(Map.Entry<String, N> reachableNodeEntry : reachableNodes.entrySet()) {
					
					String edgeKey = reachableNodeEntry.getKey();
					E edge = edges.get(edgeKey);
					N to = reachableNodeEntry.getValue();

					if(from != null && edge != null && to != null) {
						nodesToWrite.remove(from.id().toKey());
						nodesToWrite.remove(to.id().toKey());
						writer.writeEdge(from, edge, to);
					}
				}
		}
		
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
}
