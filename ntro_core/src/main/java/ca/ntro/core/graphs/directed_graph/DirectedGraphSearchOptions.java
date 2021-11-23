package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.SearchStrategy;

public class DirectedGraphSearchOptions extends SearchOptionsNtro implements SearchOptions {

	@Override
	protected Direction[] defaultDirections() {
		return new Direction[] {Direction.FORWARD};
	}

	public DirectedGraphSearchOptions() {
	}

	public DirectedGraphSearchOptions(SearchStrategy searchStrategy) {
		super(searchStrategy);
	}

	public DirectedGraphSearchOptions(SearchStrategy searchStrategy, Direction[] directions) {
		super(searchStrategy);
		setDirections(directions);
	}

	public DirectedGraphSearchOptions(SearchStrategy searchStrategy, int maxDistance) {
		super(searchStrategy, maxDistance);
	}

	public DirectedGraphSearchOptions(SearchStrategy searchStrategy, Direction[] directions, int maxDistance) {
		super(searchStrategy, maxDistance);
		setDirections(directions);
	}

	public DirectedGraphSearchOptions(int maxDistance) {
		super(maxDistance);
	}

	public DirectedGraphSearchOptions(Direction[] directions) {
		super(directions);
	}


	public DirectedGraphSearchOptions(Direction[] directions, int maxDistance) {
		super(maxDistance);
		setDirections(directions);
	}
}
