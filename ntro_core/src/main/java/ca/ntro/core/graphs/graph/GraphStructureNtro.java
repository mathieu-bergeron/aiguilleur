package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeId;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
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

	@Override
	protected void memorizeDirectedEdge(Node<NV> from, Edge<EV> edge, Node<NV> to) {
		if(from.id().toKey().compareTo(to.id().toKey()) < 0) {
			
			edgesForward.addEdge(from, edge, to);
			
		}else {

			edgesForward.addEdge(to, edge, from);
		}
	}

	@Override
	protected EdgeId directedEdgeId(Node<NV> from, EV edgeValue, Node<NV> to) {
		EdgeId edgeId = null;

		if(from.id().toKey().compareTo(to.id().toKey()) < 0) {
			
			edgeId = new EdgeId(from.id(), edgeValue.name(), to.id());

		}else {

			edgeId = new EdgeId(to.id(), edgeValue.name(), from.id());

		}
		
		return edgeId;
	}

}
