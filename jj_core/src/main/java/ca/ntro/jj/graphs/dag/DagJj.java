package ca.ntro.jj.graphs.dag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ntro.jj.graphs.dag.directions.Direction;

public class DagJj<N extends Node, E extends Edge> implements Dag<N,E> {
	
	private Map<String, N> nodes = new HashMap<>();
	
	@Override
	public void addNode(N n) {
		
	}

	@Override
	public void addEdge(N from, E edge, N to) throws CycleException {
		
	}

	@Override
	public void addEdge(NodeId from, E edge, NodeId to) throws CycleException {
		
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
		
	}

}
