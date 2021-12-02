package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.SearchOptionsNtro;

public class ObjectGraphSearchOptions extends SearchOptionsNtro<ObjectGraphSearchOptions> {

	protected Direction[] defaultDirections() {
		return new Direction[] {Direction.FORWARD};
	}

	protected boolean defaultSortByEdgeNames() {
		return true;
	}
}
