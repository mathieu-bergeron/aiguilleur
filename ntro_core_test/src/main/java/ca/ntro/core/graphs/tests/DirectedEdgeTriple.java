package ca.ntro.core.graphs.tests;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptions;

public class DirectedEdgeTriple<N extends Node<N,E,SO>,
                                E extends Edge<N,E,SO>,
                                SO extends SearchOptions> {
	
	protected N from;
	protected EdgeType edgeType;
	protected N to;
	
	public DirectedEdgeTriple(N from, EdgeType edgeType, N to) {
		this.from = from;
		this.edgeType = edgeType;
		this.to = to;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(o instanceof DirectedEdgeTriple) {
			DirectedEdgeTriple<N,E,SO> e = (DirectedEdgeTriple<N,E,SO>) o;
			
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

}
