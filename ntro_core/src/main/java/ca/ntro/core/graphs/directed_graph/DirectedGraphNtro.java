package ca.ntro.core.graphs.directed_graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.graphs.generic_graph.Direction;
import ca.ntro.core.graphs.generic_graph.Edge;
import ca.ntro.core.graphs.generic_graph.GenericGraphNtro;
import ca.ntro.core.graphs.generic_graph.Node;

public class      DirectedGraphNtro<N extends Node, E extends Edge> 
       extends    GenericGraphNtro<DirectedGraphSearchOptions,N,E> 
       implements DirectedGraph<N,E> {

	private Map<String, Map<String, N>> edgesBackward = new HashMap<>();
	
	protected Map<String, Map<String, N>> getEdgesBackward() {
		return edgesBackward;
	}

	protected void setEdgesBackward(Map<String, Map<String, N>> edgesBackward) {
		this.edgesBackward = edgesBackward;
	}

	@Override
	protected DirectedGraphSearchOptions defaultSearchOptions() {
		return new DirectedGraphSearchOptions();
	}

	@Override
	protected <R extends Object> Set<String> reachableNodesOneStep(Set<String> visitedNodes, 
			                                                       N from, 
			                                                       Direction direction) {
		
			Set<String> result = new HashSet<>();

			if(direction == Direction.FORWARD) {

				result = reachableNodesOneStep(visitedNodes, from, getEdgesForward());

			}else if(direction == Direction.BACKWARD) {

				result = reachableNodesOneStep(visitedNodes, from, getEdgesBackward());
			}
			
			return result;
	}

	@Override
	protected void addToEdgesMaps(N from, E edge, N to) {

		addToEdgesMap(getEdgesForward(), from, edge, to);
		addToEdgesMap(getEdgesBackward(), to, edge, from);
	}

	@Override
	protected void detectCycleFrom(N from) {
	}
}
