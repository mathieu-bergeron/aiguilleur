package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeId;
import ca.ntro.core.graphs.EdgeValue;

public class EdgeNtro<EV extends EdgeValue> implements Edge<EV> {
	
	private EdgeId id;
	private EV value;

	public EdgeNtro(EdgeId id, EV value) {
		this.id = id;
		this.value = value;
	}

	@Override
	public EdgeId id() {
		return id;
	}

	@Override
	public EV value() {
		return value;
	}

}
