package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.common.EdgeId;
import ca.ntro.core.graphs.common.EdgeIdNtro;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.identifyers.Name;
import ca.ntro.core.path.Filepath;

public class     GenericEdgeNtro<N extends GenericNode<N,E,SO>, 
                          E extends GenericEdge<N,E,SO>,
                          SO extends SearchOptions> 

      implements GenericEdge<N,E,SO> {

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

	public GenericEdgeNtro() {
	}

	public GenericEdgeNtro(N from, EdgeType edgeType, N to) {
		setFrom(from);
		setEdgeType(edgeType);
		setTo(to);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(o instanceof GenericEdge) {
			GenericEdge<N,E,SO> e = (GenericEdge<N,E,SO>) o;

			if(!equals(e.from(), from())) {
				return false;
			}

			if(!equals(e.type(), type())) {
				return false;
			}

			if(!equals(e.to(), to())) {
				return false;
			}
		
			return true;
		}
		
		return false;
	}

	protected boolean equals(Object a, Object b) {
		if(a == null && b != null) {
			return false;
		}
		
		if(a != null && !a.equals(b)) {
			return false;
		}
		
		return true;
	}

	protected boolean equalsUndirected(EdgeType typeA, EdgeType typeB) {
		if(typeA == null && typeB != null) {
			return false;
		}
		
		if(typeA != null && !typeA.equalsUndirected(typeB)) {
			return false;
		}
		
		return true;
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
		Filepath path = Filepath.fromSingleName(from().id().toKey().toString());
		path.addName(type().direction().name());
		path.addName(type().name().toString());
		path.addName(to().id().toKey().toString());
		
		return new EdgeIdNtro(path.toKey());
	}

	@Override
	public Name name() {
		return getEdgeType().name();
	}

	@Override
	public boolean equalsUndirected(E edge) {
		return equals(from(), edge.from())
				&& equals(to(), edge.to())
				&& equalsUndirected(type(), edge.type());
	}
}
