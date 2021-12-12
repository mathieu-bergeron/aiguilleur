package ca.ntro.core.graphs.graph_writer;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.path.Path;

public class EdgeSpecNtro implements EdgeSpec {

	private GenericEdge<?,?,?> edge;
	
	public GenericEdge<?,?,?> getEdge() {
		return edge;
	}

	public void setEdge(GenericEdge<?,?,?> edge) {
		this.edge = edge;
	}
	
	public EdgeSpecNtro() {

	}

	public EdgeSpecNtro(GenericEdge<?,?,?> edge) {
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
