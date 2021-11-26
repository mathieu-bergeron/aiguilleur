package ca.ntro.core.graphs.tests;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;

public class UndirectedEdgeTriple<NV extends NodeValue, EV extends EdgeValue> {
	
	private Node<NV> from;
	private Edge<EV> edge;
	private Node<NV> to;
	
	public UndirectedEdgeTriple(Node<NV> from, Edge<EV> edge, Node<NV> to) {
		this.from = from;
		this.edge = edge;
		this.to = to;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(o instanceof UndirectedEdgeTriple) {
			UndirectedEdgeTriple<NV,EV> e = (UndirectedEdgeTriple<NV,EV>) o;

			if(e.edge == null && edge != null) {
				return false;
			}

			if(e.edge != null && !e.edge.equals(edge)) {
				return false;
			}
			
			if(equalsForward(e)) {

				return true;

			}
				
			return equalsBackward(e);
		}
		
		return false;
	}

	private boolean equalsForward(UndirectedEdgeTriple<NV, EV> e) {
		return equalsForward(e.from, e.to);
	}

	private boolean equalsBackward(UndirectedEdgeTriple<NV, EV> e) {
		return equalsForward(e.to, e.from);
	}

	private boolean equalsForward(Node<NV> otherFrom, Node<NV> otherTo) {
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
