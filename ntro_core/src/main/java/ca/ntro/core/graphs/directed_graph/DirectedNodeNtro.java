package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedNodeNtro;

public class DirectedNodeNtro<N extends  DirectedNode<N,E>, 
                              E extends  DirectedEdge<N,E>>

       extends GenericDirectedNodeNtro<N,
                                       E,
                                       DirectedGraphSearchOptions>


	   implements DirectedNode<N,E> {

	public DirectedNodeNtro() {
	}

	public DirectedNodeNtro(NodeId nodeId) {
		super(nodeId);
	}

	public DirectedNodeNtro(String nodeId) {
		super(nodeId);
	}


}
