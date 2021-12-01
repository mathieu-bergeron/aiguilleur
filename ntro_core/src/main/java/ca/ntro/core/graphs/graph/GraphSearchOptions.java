package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.SearchStrategy;
import ca.ntro.core.wrappers.optionnal.Optionnal;

public class GraphSearchOptions extends SearchOptionsNtro {

	public GraphSearchOptions() {
		super();
	}


	public GraphSearchOptions(Direction[] directions) {
		super(directions);
	}

	public GraphSearchOptions(SearchStrategy searchStrategy, Direction[] directions, Optionnal<Integer> maxDistance) {
		super(searchStrategy, directions, maxDistance);
	}

	public GraphSearchOptions(Direction[] directions, Optionnal<Integer> maxDistance) {
		super(directions, maxDistance);
	}


	@Override
	protected Direction[] defaultDirections() {
		return new Direction[] {Direction.FORWARD};
	}

}
