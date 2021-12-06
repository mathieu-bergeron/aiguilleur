package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.Walk;

public class ReachedNodeNtro <N extends Node<N,E,SO>, 
                              E extends Edge<N,E,SO>,
                              SO extends SearchOptionsBuilder> 

       extends ReachedItemNtro<N,E,SO>

       implements ReachedNode<N,E,SO> {
	
	private N node;

	public N getNode() {
		return node;
	}

	public void setNode(N node) {
		this.node = node;
	}
	
	public ReachedNodeNtro() {
	}
	
	public ReachedNodeNtro(Walk<N,E,SO> walked, N node) {
		super(walked);
		setNode(node);
	}

	@Override
	public N node() {
		return getNode();
	}
}
