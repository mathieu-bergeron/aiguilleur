package ca.ntro.core.graphs.directed_graph;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeId;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.EdgeWalkVisitor;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.path.EdgeWalk;

public class      DirectedGraphNtro<NV extends NodeValue, EV extends EdgeValue> 
       extends    GenericGraphBuilderNtro<DirectedGraphSearchOptions,NV,EV,DirectedGraph<NV,EV>> 
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
	protected void addToEdgesMaps(Node<NV> from, Edge<EV> edge, Node<NV> to) {

		addToEdgesMap(getEdgesForward(), from, edge, to);
		addToEdgesMap(getEdgesBackward(), to, edge, from);
	}

	@Override
	protected EdgeId newEdgeId(Node<NV> from, EV edgeValue, Node<NV> to) {
		return new EdgeId(from.id(), edgeValue.name(), to.id());
	}

	@Override
	protected void detectCycleFrom(Node<NV> from) {
	}

	@Override
	protected Map<String, Map<String, Node<NV>>> edgesMapForDirection(Direction direction) {
		
		Map<String, Map<String, Node<NV>>> edgesMap = null;
		
		if(direction == Direction.FORWARD) {

			edgesMap = getEdgesForward();

		}else if(direction == Direction.BACKWARD) {
			
			edgesMap = getEdgesBackward();
		}
			
		return edgesMap;
	}


}
