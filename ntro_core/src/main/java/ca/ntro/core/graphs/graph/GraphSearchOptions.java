package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.SearchOptionsNtro;

public class GraphSearchOptions<SO extends GraphSearchOptions<SO>> extends SearchOptionsNtro<SO> {

	public GraphSearchOptions() {
		super();
	}

	@Override
	protected Direction[] defaultDirections() {
		return new Direction[] {Direction.FORWARD};
	}

}
