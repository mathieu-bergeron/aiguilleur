package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.graph.GraphSearchOptions;

public class HierarchicalGraphSearchOptions<SO extends HierarchicalGraphSearchOptions<SO>> extends GraphSearchOptions<SO> {

	@Override
	protected Direction[] defaultDirections() {
		return new Direction[] {Direction.DOWN, Direction.FORWARD};
	}

}
