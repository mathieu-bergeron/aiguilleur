package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;

public class EdgeSpecNtro implements EdgeSpec {

	private Edge<? extends EdgeValue> edge;
	
	public Edge<? extends EdgeValue> getEdge() {
		return edge;
	}

	public void setEdge(Edge<? extends EdgeValue> edge) {
		this.edge = edge;
	}
	
	public EdgeSpecNtro(Edge<? extends EdgeValue> edge) {
		setEdge(edge);
	}
	

	@Override
	public String id() {
		return edge.id().toKey();
	}

	@Override
	public String label() {
		return edge.value().label();
	}
}
