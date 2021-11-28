package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeName;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptions;

public class     EdgeNtro<N extends Node<N,E,SO>, 
                          E extends Edge<N,E,SO>,
                          SO extends SearchOptions> 

      implements Edge<N,E,SO> {

	private N from;
	private EdgeName edgeId;
	private N to;

	public N getFrom() {
		return from;
	}

	public void setFrom(N from) {
		this.from = from;
	}

	public EdgeName getEdgeId() {
		return edgeId;
	}

	public void setEdgeId(EdgeName edgeId) {
		this.edgeId = edgeId;
	}

	public N getTo() {
		return to;
	}

	public void setTo(N to) {
		this.to = to;
	}

	public EdgeNtro(N from, EdgeName edgeId, N to) {
		setFrom(from);
		setEdgeId(edgeId);
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
			
			if(e.edgeId == null && edgeId != null) {
				return false;
			}
			
			if(e.edgeId != null && !e.edgeId.equals(edgeId)) {
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
	public EdgeName id() {
		return getEdgeId();
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
