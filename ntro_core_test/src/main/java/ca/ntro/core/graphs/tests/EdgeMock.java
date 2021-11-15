package ca.ntro.core.graphs.tests;

import ca.ntro.core.graphs.generic_graph.Edge;
import ca.ntro.core.graphs.generic_graph.EdgeId;

public class EdgeMock implements Edge {
	
	private EdgeId id;
	
	public EdgeMock(String id) {
		this.id = new EdgeId(id);
	}

	@Override
	public EdgeId id() {
		return id;
	}

	@Override
	public String label() {
		return id.toString();
	}

}
