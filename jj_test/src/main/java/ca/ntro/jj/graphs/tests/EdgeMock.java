package ca.ntro.jj.graphs.tests;

import ca.ntro.jj.graphs.dag.Edge;
import ca.ntro.jj.graphs.dag.EdgeId;

public class EdgeMock implements Edge {
	
	private EdgeId id;
	
	public EdgeMock(String id) {
		this.id = new EdgeId(id);
	}

	@Override
	public EdgeId id() {
		return id;
	}

}
