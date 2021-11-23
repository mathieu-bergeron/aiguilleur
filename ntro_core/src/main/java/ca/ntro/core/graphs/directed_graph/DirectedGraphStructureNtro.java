package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeId;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphStructureNtro;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.EdgesForFromNode;

public class      DirectedGraphStructureNtro<NV extends NodeValue, EV extends EdgeValue> 
       extends    GenericGraphStructureNtro<NV,EV>
       implements DirectedGraphStructure<NV,EV> {

	private EdgesForFromNode<NV,EV> edgesForward = new EdgesForFromNode<NV,EV>();
	private EdgesForFromNode<NV,EV> edgesBackward = new EdgesForFromNode<NV,EV>();

	@Override
	protected EdgesForFromNode<NV,EV> edgesByDirection(Direction direction) {
		EdgesForFromNode<NV,EV> edges = null;
		
		if(direction == Direction.FORWARD) {

			edges = edgesForward;

		}else if(direction == Direction.BACKWARD) {

			edges = edgesBackward;
			
		}
		
		return edges;
	}

	@Override
	protected void memorizeDirectedEdge(Node<NV> from, Edge<EV> edge, Node<NV> to) {
		edgesForward.addEdge(from, edge, to);
		edgesBackward.addEdge(to, edge, from);
	}

	@Override
	protected EdgeId directedEdgeId(Node<NV> from, EV edgeValue, Node<NV> to) {
		return new EdgeId(from.id(), edgeValue.name(), to.id());
	}

}
