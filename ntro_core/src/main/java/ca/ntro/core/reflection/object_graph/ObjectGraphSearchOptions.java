package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.SearchOptionsBuilderNtro;

public class ObjectGraphSearchOptions extends SearchOptionsBuilderNtro {

	protected Direction[] defaultDirections() {
		return new Direction[] {Direction.FORWARD};
	}

	protected boolean defaultSortByEdgeNames() {
		return true;
	}
}
