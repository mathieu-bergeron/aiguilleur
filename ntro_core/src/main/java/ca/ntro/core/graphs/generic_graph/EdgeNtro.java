package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptions;

public class     EdgeNtro<N extends Node<N,E,SO>, 
                          E extends Edge<N,E,SO>,
                          SO extends SearchOptions> 

      implements Edge<N,E,SO> {

	private N from;
	private EdgeType edgeName;
	private N to;

	public N getFrom() {
		return from;
	}

	public void setFrom(N from) {
		this.from = from;
	}

	public EdgeType getEdgeName() {
		return edgeName;
	}

	public void setEdgeName(EdgeType edgeName) {
		this.edgeName = edgeName;
	}

	public N getTo() {
		return to;
	}

	public void setTo(N to) {
		this.to = to;
	}

	public EdgeNtro(N from, EdgeType edgeName, N to) {
		setFrom(from);
		setEdgeName(edgeName);
		setTo(to);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(o instanceof NodeNtro) {
			EdgeNtro e = (EdgeNtro) o;

			if(e.from == null && from != null) {
				return false;
			}
			
			if(e.from != null && !e.from.equals(from)) {
				return false;
			}
			
			if(e.edgeName == null && edgeName != null) {
				return false;
			}
			
			if(e.edgeName != null && !e.edgeName.equals(edgeName)) {
				return false;
			}

			if(e.to == null && to != null) {
				return false;
			}
			
			if(e.to != null && !e.to.equals(to)) {
				return false;
			}

			return true;
		}
		
		return false;
	}

	@Override
	public EdgeType type() {
		return getEdgeName();
	}

	@Override
	public N from() {
		return getFrom();
	}

	@Override
	public N to() {
		return getTo();
	}
}
