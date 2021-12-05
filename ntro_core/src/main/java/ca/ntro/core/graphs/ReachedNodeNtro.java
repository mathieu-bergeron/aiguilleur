package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.Walk;

public class ReachedNodeNtro <N extends Node<N,E,SO>, 
                              E extends Edge<N,E,SO>,
                              SO extends SearchOptionsBuilder> 

       implements ReachedNode<N,E,SO> {
	
	private Walk<N,E,SO> walked;
	private N node;

	public Walk<N, E, SO> getWalked() {
		return walked;
	}

	public void setWalked(Walk<N, E, SO> walked) {
		this.walked = walked;
	}

	public N getNode() {
		return node;
	}

	public void setNode(N node) {
		this.node = node;
	}
	
	public ReachedNodeNtro() {
	}
	
	public ReachedNodeNtro(Walk<N,E,SO> walked, N node) {
		setWalked(walked);
		setNode(node);
	}
	
	@Override
	public Walk<N, E, SO> walked() {
		return getWalked();
	}

	@Override
	public N node() {
		return getNode();
	}
	

}
