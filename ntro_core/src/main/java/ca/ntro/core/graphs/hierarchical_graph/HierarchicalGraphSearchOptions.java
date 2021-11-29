package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.SearchStrategy;
import ca.ntro.core.graphs.graph.GraphSearchOptions;
import ca.ntro.core.wrappers.optionnal.Optionnal;

public class HierarchicalGraphSearchOptions extends GraphSearchOptions {

	public HierarchicalGraphSearchOptions() {
		super();
	}

	public HierarchicalGraphSearchOptions(SearchStrategy searchStrategy, Direction[] directions, Optionnal<Integer> maxDistance) {
		super(searchStrategy, directions, maxDistance);
	}


	@Override
	protected Direction[] defaultDirections() {
		return new Direction[] {Direction.DOWN, Direction.FORWARD};
	}

}
