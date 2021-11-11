package ca.ntro.core.graphs.dag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.dag.directions.Direction;
import ca.ntro.core.graphs.dag.exceptions.CycleException;
import ca.ntro.core.graphs.dag.exceptions.NodeNotFoundException;

public class DagNtro<N extends Node, E extends Edge> implements Dag<N,E> {
	
	
	private GraphId id;
	private Map<String, N> nodes = new HashMap<>();
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

	@Override
	public void addEdge(N from, E edge, N to) throws CycleException {
		
	}

	@Override
	public void addEdge(NodeId from, E edge, NodeId to) throws NodeNotFoundException, CycleException {
		
	}

	@Override
	public void addEdge(NodeMatcher from, E edge, NodeMatcher to) throws NodeNotFoundException, CycleException {
		
	}

	@Override
	public void addEdge(String from, E edge, String to) throws NodeNotFoundException, CycleException {
		
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
		
		for(N node : nodes.values()) {
			writer.writeNode(node);
		}
	}

	@Override
	public GraphId id() {
		return this.id;
	}
}
