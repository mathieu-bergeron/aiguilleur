package ca.ntro.core.graphs.tests;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;

public class DirectedEdgeTriple<NV extends NodeValue, EV extends EdgeValue> {
	
	private Node<NV> from;
	private Edge<EV> edge;
	private Node<NV> to;
	
	public DirectedEdgeTriple(Node<NV> from, Edge<EV> edge, Node<NV> to) {
		this.from = from;
		this.edge = edge;
		this.to = to;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(o instanceof DirectedEdgeTriple) {
			DirectedEdgeTriple<NV,EV> e = (DirectedEdgeTriple<NV,EV>) o;
			
			if(e.from == null && from != null) {
				return false;
			}

			if(e.from != null && !e.from.equals(from)) {
				return false;
			}
			
			
			
			if(e.edge == null && edge != null) {
				return false;
			}

			if(e.edge != null && !e.edge.equals(edge)) {
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
