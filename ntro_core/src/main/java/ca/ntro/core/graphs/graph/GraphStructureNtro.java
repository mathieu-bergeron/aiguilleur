package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeName;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.Step;
import ca.ntro.core.graphs.generic_graph.GenericGraphStructureNtro;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.EdgesForFromNode;
import ca.ntro.core.identifyers.Key;

public class        GraphStructureNtro<NV extends NodeValue, 
                                       EV extends EdgeValue,
                                       N extends Node<NV>,
                                       E extends Edge<EV>> 

       extends      GenericGraphStructureNtro<NV,EV,N,E>
       implements   GraphStructure<NV,EV,N,E> {

	private EdgesForFromNode<NV,EV,N,E> edgesForward = new EdgesForFromNode<NV,EV,N,E>();

	@Override
	protected EdgesForFromNode<NV,EV,N,E> edgesByDirection(Direction direction) {
		EdgesForFromNode<NV,EV,N,E> edges = null;
		
		if(direction == Direction.FORWARD) {
			edges = edgesForward;
		}
		
		return edges;
	}

	@Override
	protected void memorizeDirectedEdge(N from, E edge, N to) {
		if(from.id().toKey().toString().compareTo(to.id().toKey().toString()) < 0) {
			
			edgesForward.addEdge(from, edge, to);
			
		}else {

			edgesForward.addEdge(to, edge, from);
		}
	}

	@Override
	protected EdgeName directedEdgeId(N from, Key edgeName, N to) {
		EdgeName edgeId = null;

		if(from.id().toKey().toString().compareTo(to.id().toKey().toString()) < 0) {
			
			edgeId = new EdgeName(from.id(), edgeName, to.id());

		}else {

			edgeId = new EdgeName(to.id(), edgeName, from.id());

		}
		
		return edgeId;
	}


}
