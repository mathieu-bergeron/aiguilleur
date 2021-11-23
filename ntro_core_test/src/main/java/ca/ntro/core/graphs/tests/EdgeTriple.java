package ca.ntro.core.graphs.tests;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;

public class EdgeTriple<NV extends NodeValue, EV extends EdgeValue> {
	
	private Node<NV> from;
	private Edge<EV> edge;
	private Node<NV> to;
	
	public EdgeTriple(Node<NV> from, Edge<EV> edge, Node<NV> to) {
		this.from = from;
		this.edge = edge;
		this.to = to;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(o instanceof EdgeTriple) {
			EdgeTriple<NV,EV> e = (EdgeTriple<NV,EV>) o;
			
			return from.equals(e.from)
					&& edge.equals(e.edge)
					&& to.equals(e.to);
		}
		
		return false;
	}

}
