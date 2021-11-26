package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.SearchOptionsNtro;

public class GraphSearchOptions extends SearchOptionsNtro {

	@Override
	protected Direction[] defaultDirections() {
		return new Direction[] {Direction.DOWN, Direction.FORWARD};
	}

}
