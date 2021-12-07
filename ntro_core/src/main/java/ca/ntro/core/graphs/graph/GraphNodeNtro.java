package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.generic_graph.GenericNodeNtro;
import ca.ntro.core.graphs.generics.generic_graph.NodeId;

public class GraphNodeNtro<N extends GraphNode<N,E>,
                           E extends GraphEdge<N,E>>

       extends GenericNodeNtro<N,E,GraphSearchOptionsBuilder>

       implements GraphNode<N,E> {

	public GraphNodeNtro(NodeId nodeId) {
		super(nodeId);
	}

	public GraphNodeNtro(String nodeId) {
		super(nodeId);
	}

	public GraphNodeNtro() {
		super();
	}

}
