package ca.ntro.core.graphs.directed_graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphNtro;

public class      DirectedGraphNtro<NV extends NodeValue, EV extends EdgeValue> 
       extends    GenericGraphNtro<DirectedGraphSearchOptions,NV,EV,DirectedGraph<NV,EV>> 
       implements DirectedGraph<NV,EV> {

	private Map<String, Map<String, Node<NV>>> edgesBackward = new HashMap<>();
	
	protected Map<String, Map<String, Node<NV>>> getEdgesBackward() {
		return edgesBackward;
	}

	protected void setEdgesBackward(Map<String, Map<String, Node<NV>>> edgesBackward) {
		this.edgesBackward = edgesBackward;
	}

	@Override
	protected DirectedGraphSearchOptions defaultSearchOptions() {
		return new DirectedGraphSearchOptions();
	}

	@Override
	protected <R extends Object> Set<String> reachableNodesOneStep(Set<String> visitedNodes, 
			                                                       Node<NV> from, 
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
	protected void addToEdgesMaps(Node<NV> from, Edge<EV> edge, Node<NV> to) {

		addToEdgesMap(getEdgesForward(), from, edge, to);
		addToEdgesMap(getEdgesBackward(), to, edge, from);
	}

	@Override
	protected void detectCycleFrom(Node<NV> from) {
	}
}
