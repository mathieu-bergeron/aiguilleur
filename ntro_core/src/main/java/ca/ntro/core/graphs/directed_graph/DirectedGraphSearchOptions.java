package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.SearchOptionsNtro;

public class DirectedGraphSearchOptions extends SearchOptionsNtro<DirectedGraphSearchOptions> implements SearchOptions<DirectedGraphSearchOptions> {

	@Override
	protected Direction[] defaultDirections() {
		return new Direction[] {Direction.FORWARD};
	}
}
