package ca.ntro.core.graphs.tests;

public class EdgeTriple {
	
	private MockNodeValue from;
	private MockEdgeValue edge;
	private MockNodeValue to;
	
	public EdgeTriple(MockNodeValue from, MockEdgeValue edge, MockNodeValue to) {
		this.from = from;
		this.edge = edge;
		this.to = to;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(o instanceof EdgeTriple) {
			EdgeTriple e = (EdgeTriple) o;
			
			return from.equals(e.from)
					&& edge.equals(e.edge)
					&& to.equals(e.to);
		}
		
		return false;
	}

}
