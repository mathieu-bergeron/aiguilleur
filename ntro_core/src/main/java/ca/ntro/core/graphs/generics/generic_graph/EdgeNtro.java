package ca.ntro.core.graphs.generics.generic_graph;

import ca.ntro.core.identifyers.Name;
import ca.ntro.core.path.Filepath;
import ca.ntro.core.path.Path;

public class     EdgeNtro<N extends Node<N,E,SO>, 
                          E extends Edge<N,E,SO>,
                          SO extends SearchOptionsBuilder> 

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

	public EdgeNtro() {
	}

	public EdgeNtro(N from, EdgeType edgeType, N to) {
		setFrom(from);
		setEdgeType(edgeType);
		setTo(to);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(o instanceof Edge) {
			Edge<N,E,SO> e = (Edge<N,E,SO>) o;
			
		}
		
		return false;
	}

	protected boolean equals(N nodeA, N nodeB) {
		if(nodeA == null && nodeB != null) {
			return false;
		}
		
		if(nodeA != null && !nodeA.equals(nodeB)) {
			return false;
		}
		
		return true;
	}

	protected boolean equals(EdgeType typeA, EdgeType typeB) {
		if(typeA == null && typeB != null) {
			return false;
		}
		
		if(typeA != null && !typeA.equals(typeB)) {
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
