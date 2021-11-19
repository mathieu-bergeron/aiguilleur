package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphStructureNtro;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.EdgesForFromNode;

public class        GraphStructureNtro<NV extends NodeValue, EV extends EdgeValue> 
       extends      GenericGraphStructureNtro<NV,EV>
       implements   GraphStructure<NV,EV> {

	private EdgesForFromNode<NV,EV> edgesForward = new EdgesForFromNode<NV,EV>();

	@Override
	protected EdgesForFromNode<NV,EV> edgesByDirection(Direction direction) {
		EdgesForFromNode<NV,EV> edges = null;
		
		if(direction == Direction.FORWARD) {
			edges = edgesForward;
		}
		
		return edges;
	}

}
