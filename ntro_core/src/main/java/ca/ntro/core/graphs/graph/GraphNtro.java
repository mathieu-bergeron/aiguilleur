package ca.ntro.core.graphs.graph;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeId;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphNtro;

public class   GraphNtro<NV extends NodeValue, EV extends EdgeValue> 
       extends GenericGraphNtro<GraphSearchOptions,NV,EV,Graph<NV,EV>> 
       implements Graph<NV,EV>, GraphBuilder<NV,EV> {


	public GraphNtro() {
		super();
	}

	public GraphNtro(String graphName) {
		super(graphName);
	}

	@Override
	protected GraphSearchOptions defaultSearchOptions() {
		return new GraphSearchOptions();
	}

	@Override
	protected void detectCycleFrom(Node<NV> from) {
	}

	@Override
	protected EdgeId newEdgeId(Node<NV> from, EV edgeValue, Node<NV> to) {
		EdgeId edgeId = null;

		if(from.id().toKey().compareTo(to.id().toKey()) < 0) {
			
			edgeId = newEdgeId(from.id().toFilepath(), edgeValue, to.id().toFilepath());

		}else {

			edgeId = newEdgeId(to.id().toFilepath(), edgeValue, from.id().toFilepath());

		}
		
		return edgeId;
	}

	@Override
	protected void addToEdgesMaps(Node<NV> from, Edge<EV> edge, Node<NV> to) {
		if(from.id().toKey().compareTo(to.id().toKey()) < 0) {

			addToEdgesMap(getEdgesForward(), from, edge, to);
			
		}else {

			addToEdgesMap(getEdgesForward(), to, edge, from);
		}
	}

	@Override
	protected <R extends Object> Set<String> reachableNodesOneStep(Set<String> visitedNodes, 
			                                                       Node<NV> from, 
			                                                       Direction direction) {
		
			Set<String> result = new HashSet<>();

			if(direction == Direction.FORWARD) {

				result = reachableNodesOneStep(visitedNodes, from, getEdgesForward());
			}
			
			return result;
	}
}
