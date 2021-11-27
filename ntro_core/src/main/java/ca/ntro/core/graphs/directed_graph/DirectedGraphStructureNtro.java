package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeId;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphStructureNtro;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.EdgesForFromNode;
import ca.ntro.core.path.PathName;

public class      DirectedGraphStructureNtro<NV extends NodeValue, 
                                             EV extends EdgeValue,
                                             N extends Node<NV>,
                                             E extends Edge<EV>> 
       extends    GenericGraphStructureNtro<NV,EV,N,E>
       implements DirectedGraphStructure<NV,EV,N,E> {

	private EdgesForFromNode<NV,EV,N,E> edgesForward = new EdgesForFromNode<NV,EV,N,E>();
	private EdgesForFromNode<NV,EV,N,E> edgesBackward = new EdgesForFromNode<NV,EV,N,E>();

	@Override
	protected EdgesForFromNode<NV,EV,N,E> edgesByDirection(Direction direction) {
		EdgesForFromNode<NV,EV,N,E> edges = null;
		
		if(direction == Direction.FORWARD) {

			edges = edgesForward;

		}else if(direction == Direction.BACKWARD) {

			edges = edgesBackward;
			
		}
		
		return edges;
	}

	@Override
	protected void memorizeDirectedEdge(N from, E edge, N to) {
		edgesForward.addEdge(from, edge, to);
		edgesBackward.addEdge(to, edge, from);
	}

	@Override
	protected EdgeId directedEdgeId(N from, PathName edgeName, N to) {
		return new EdgeId(from.id(), edgeName, to.id());
	}

}
