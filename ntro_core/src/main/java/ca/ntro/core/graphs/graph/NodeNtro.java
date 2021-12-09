package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.generics.graph.GenericNodeNtro;

public class NodeNtro<N extends Node<N,E>,
                           E extends Edge<N,E>>

       extends GenericNodeNtro<N,E,GraphSearchOptions>

       implements Node<N,E> {

	public NodeNtro() {
		super();
	}

	public NodeNtro(NodeId nodeId) {
		super(nodeId);
	}

	public NodeNtro(String nodeId) {
		super(nodeId);
	}
}
