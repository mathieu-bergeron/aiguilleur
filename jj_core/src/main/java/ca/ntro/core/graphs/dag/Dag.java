package ca.ntro.core.graphs.dag;

import java.util.List;

import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.dag.directions.Direction;
import ca.ntro.core.graphs.dag.exceptions.CycleException;
import ca.ntro.core.graphs.dag.exceptions.NodeNotFoundException;

public interface Dag<N extends Node, E extends Edge> {
	
	GraphId id();
	
	void addNode(N n);

	void addEdge(N from, E edge, N to) throws CycleException;
	void addEdge(NodeId from, E edge, NodeId to) throws NodeNotFoundException, CycleException;
	void addEdge(NodeMatcher from, E edge, NodeMatcher to) throws NodeNotFoundException, CycleException;
	void addEdge(String from, E edge, String to) throws NodeNotFoundException, CycleException;
	
	void forEachNode(NodeVisitor<N> visitor);
	void forEachEdge(EdgeVisitor<N,E> visistor);
	
	void forEachReachableNode(N from, List<Direction> directions);
	
	void write(DagWriter<N,E> writer);

}
