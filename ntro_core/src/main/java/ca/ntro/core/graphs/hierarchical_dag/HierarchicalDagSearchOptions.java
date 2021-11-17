package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.SearchStrategy;
import ca.ntro.core.graphs.directed_graph.DirectedGraphSearchOptions;

public class HierarchicalDagSearchOptions extends DirectedGraphSearchOptions implements SearchOptions {

	@Override
	protected Direction[] defaultDirections() {
		return new Direction[] {Direction.FORWARD, Direction.DOWN_TO_SUB_NODES};
	}
	
	public HierarchicalDagSearchOptions() {
	}

	public HierarchicalDagSearchOptions(SearchStrategy searchStrategy) {
		super(searchStrategy);
	}

	public HierarchicalDagSearchOptions(SearchStrategy searchStrategy, Direction[] directions) {
		super(searchStrategy, directions);
	}

	public HierarchicalDagSearchOptions(SearchStrategy searchStrategy, int maxDistance) {
		super(searchStrategy, maxDistance);
	}

	public HierarchicalDagSearchOptions(SearchStrategy searchStrategy, Direction[] directions, int maxDistance) {
		super(searchStrategy, directions, maxDistance);
	}

	public HierarchicalDagSearchOptions(int maxDistance) {
		super(maxDistance);
	}


	public HierarchicalDagSearchOptions(Direction[] directions, int maxDistance) {
		super(directions, maxDistance);
	}
}
