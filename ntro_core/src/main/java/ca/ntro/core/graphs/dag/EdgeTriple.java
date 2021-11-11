package ca.ntro.core.graphs.dag;

public class EdgeTriple<N extends Node, E extends Edge> {
	
	private N from;
	private E edge;
	private N to;
	
	public EdgeTriple(N from, E edge, N to) {
		this.from = from;
		this.edge = edge;
		this.to = to;
	}

	public N from() {
		return from;
	}

	public N to() {
		return to;
	}

}
