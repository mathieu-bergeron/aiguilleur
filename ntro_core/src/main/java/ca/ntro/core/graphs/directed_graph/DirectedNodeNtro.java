package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.generics.directed_graph.DirectedSearchOptions;
import ca.ntro.core.graphs.generics.graph.GenericNodeNtro;

public class DirectedNodeNtro<N extends  DirectedNode<N,E>, 
                              E extends  DirectedEdge<N,E>>

       extends GenericNodeNtro<N,E,DirectedSearchOptions>


	   implements DirectedNode<N,E> {

	public DirectedNodeNtro() {
	}

	public DirectedNodeNtro(NodeId nodeId) {
		super(nodeId);
	}


}
