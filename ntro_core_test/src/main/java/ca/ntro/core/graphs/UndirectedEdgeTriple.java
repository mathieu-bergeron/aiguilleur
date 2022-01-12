package ca.ntro.core.graphs;

import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericNode;
import ca.ntro.core.graphs.generics.graph.SearchOptions;

public class  UndirectedEdgeTriple<N extends GenericNode<N,E,SO>,
								   E extends GenericEdge<N,E,SO>,
								   SO extends SearchOptions> 
	
	extends   DirectedEdgeTriple<N,E,SO>  {
	
	public UndirectedEdgeTriple(N from, EdgeType edgeType, N to) {
		super(from, edgeType, to);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(o instanceof UndirectedEdgeTriple) {
			UndirectedEdgeTriple<N,E,SO> e = (UndirectedEdgeTriple<N,E,SO>) o;

			if(e.edgeType == null && edgeType != null) {
				return false;
			}

			if(e.edgeType != null && !e.edgeType.equals(edgeType)) {
				return false;
			}
			
			if(equalsForward(e)) {

				return true;

			}
				
			return equalsBackward(e);
		}
		
		return false;
	}

	private boolean equalsForward(UndirectedEdgeTriple<N,E,SO> e) {
		return equalsForward(e.from, e.to);
	}

	private boolean equalsBackward(UndirectedEdgeTriple<N,E,SO> e) {
		return equalsForward(e.to, e.from);
	}

	// JSWeet error: property 'equals' does not exist on type 'N'
	private boolean equalsForward(Object otherFrom, Object otherTo) {
		if(otherFrom == null && from != null) {
			return false;
		}

		if(otherFrom != null && !otherFrom.equals(from)) {
			return false;
		}

		if(otherTo == null && to != null) {
			return false;
		}

		if(otherTo != null && !otherTo.equals(to)) {
			return false;
		}

		return true;
	}

}
