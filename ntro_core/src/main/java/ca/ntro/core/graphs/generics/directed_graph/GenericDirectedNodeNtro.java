package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.generics.graph.GenericNodeNtro;

public class GenericDirectedNodeNtro<N extends GenericDirectedNode<N,E,SO>, 
                                     E extends GenericDirectedEdge<N,E,SO>,
                                     SO extends DirectedGraphSearchOptions> 

       extends   GenericNodeNtro<N,E,SO> 

	   implements GenericDirectedNode<N,E,SO> {

	public GenericDirectedNodeNtro() {
		super();
	}

	public GenericDirectedNodeNtro(NodeId nodeId) {
		super(nodeId);
	}

	public GenericDirectedNodeNtro(String nodeId) {
		super(nodeId);
	}
}
