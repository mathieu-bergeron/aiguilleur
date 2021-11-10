package ca.ntro.jj.graphs.dag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ntro.jj.graphs.GraphId;
import ca.ntro.jj.graphs.dag.directions.Direction;
import ca.ntro.jj.graphs.dag.exceptions.CycleException;
import ca.ntro.jj.graphs.dag.exceptions.NodeNotFoundException;

public class DagJj<N extends Node, E extends Edge> implements Dag<N,E> {
	
	
	private GraphId id;
	private Map<String, N> nodes = new HashMap<>();
	private Map<String, Edge> edges = new HashMap<>();
	
	public DagJj() {
		this.id = GraphId.newGraphId();
	}

	public DagJj(String graphName) {
		this.id = GraphId.fromGraphName(graphName);
	}
	
	@Override
	public void addNode(N n) {
		nodes.put(n.id().toString(), n);
	}

	@Override
	public void addEdge(N from, E edge, N to) throws CycleException {
		
	}

	@Override
	public void addEdge(NodeId from, E edge, NodeId to) throws NodeNotFoundException, CycleException {
		
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
