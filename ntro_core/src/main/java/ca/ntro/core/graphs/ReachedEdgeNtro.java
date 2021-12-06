package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.Walk;

public class ReachedEdgeNtro<N extends Node<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder> 

       extends ReachedItemNtro<N,E,SO>

       implements ReachedEdge<N,E,SO> {
	
	private E Edge;

	public E getEdge() {
		return Edge;
	}

	public void setEdge(E edge) {
		Edge = edge;
	}
	
	public ReachedEdgeNtro() {
	}

	public ReachedEdgeNtro(Walk<N,E,SO> walked, E edge) {
		super(walked);
		setEdge(edge);
	}

	@Override
	public E edge() {
		return getEdge();
	}
}
