package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeTypeNtro;
import ca.ntro.core.graphs.generics.graph.GenericEdgeNtro;

public class DirectedEdgeNtro<N extends  DirectedNode<N,E>, 
                              E extends  DirectedEdge<N,E>>

       extends GenericEdgeNtro<N,E,DirectedSearchOptions> 
  
       implements DirectedEdge<N,E> {

	public DirectedEdgeNtro(N fromNode, String edgeName, N toNode) {
		super(fromNode, new EdgeTypeNtro(Direction.FORWARD, edgeName), toNode);
	}

}