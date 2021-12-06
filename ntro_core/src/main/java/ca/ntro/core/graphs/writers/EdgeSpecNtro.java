package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.path.Path;

public class EdgeSpecNtro implements EdgeSpec {

	private Edge<?,?,?> edge;
	
	public Edge<?,?,?> getEdge() {
		return edge;
	}

	public void setEdge(Edge<?,?,?> edge) {
		this.edge = edge;
	}
	
	public EdgeSpecNtro(Edge<?,?,?> edge) {
		setEdge(edge);
	}
	

	@Override
	public String id() {
		Path path = Path.fromSingleName(edge.from().id().toKey().toString());
		path.addName(edge.type().name().toString());
		path.addName(edge.to().id().toKey().toString());

		return path.toKey();
	}

	@Override
	public String label() {
		return edge.type().label();
	}
}
