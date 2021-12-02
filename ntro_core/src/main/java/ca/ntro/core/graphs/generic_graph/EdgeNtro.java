package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeId;
import ca.ntro.core.graphs.EdgeIdNtro;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.identifyers.Name;
import ca.ntro.core.path.Path;

public class     EdgeNtro<N extends Node<N,E,SO>, 
                          E extends Edge<N,E,SO>,
                          SO extends SearchOptionsNtro> 

      implements Edge<N,E,SO> {

	private N from;
	private EdgeType edgeType;
	private N to;

	public N getFrom() {
		return from;
	}

	public void setFrom(N from) {
		this.from = from;
	}

	public EdgeType getEdgeType() {
		return edgeType;
	}

	public void setEdgeType(EdgeType edgeType) {
		this.edgeType = edgeType;
	}

	public N getTo() {
		return to;
	}

	public void setTo(N to) {
		this.to = to;
	}

	public EdgeNtro(N from, EdgeType edgeType, N to) {
		setFrom(from);
		setEdgeType(edgeType);
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
			
			if(e.edgeType == null && edgeType != null) {
				return false;
			}
			
			if(e.edgeType != null && !e.edgeType.equals(edgeType)) {
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
		return getEdgeType();
	}

	@Override
	public N from() {
		return getFrom();
	}

	@Override
	public N to() {
		return getTo();
	}

	@Override
	public EdgeId id() {
		Path path = Path.fromSingleName(from().id().toKey().toString());
		path.addName(type().direction().name());
		path.addName(type().name().toString());
		path.addName(to().id().toKey().toString());
		
		return new EdgeIdNtro(path.toKey());
	}

	@Override
	public Name name() {
		return getEdgeType().name();
	}
}
