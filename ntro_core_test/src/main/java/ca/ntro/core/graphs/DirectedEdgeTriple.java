package ca.ntro.core.graphs;

import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericNode;
import ca.ntro.core.graphs.generics.graph.SearchOptions;

public class DirectedEdgeTriple<N extends GenericNode<N,E,SO>,
                                E extends GenericEdge<N,E,SO>,
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

			// JSWeet error: property 'equals' does not exist on type 'N'
			if(e.from != null && !((Object) e.from).equals(from)) {
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

			// JSWeet error: property 'equals' does not exist on type 'N'
			if(e.to != null && !((Object) e.to).equals(to)) {
				return false;
			}
			
			
			return true;
		}
		
		return false;
	}

}
