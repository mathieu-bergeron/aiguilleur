package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.Walk;

public class ReachedItemNtro<N extends Node<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder> 

	   implements ReachedItem<N,E,SO> {

	private Walk<N,E,SO> walked;

	public Walk<N, E, SO> getWalked() {
		return walked;
	}

	public void setWalked(Walk<N, E, SO> walked) {
		this.walked = walked;
	}

	public ReachedItemNtro() {
	}
	
	public ReachedItemNtro(Walk<N,E,SO> walked) {
		setWalked(walked);
	}
	
	@Override
	public Walk<N, E, SO> walked() {
		return getWalked();
	}
}
