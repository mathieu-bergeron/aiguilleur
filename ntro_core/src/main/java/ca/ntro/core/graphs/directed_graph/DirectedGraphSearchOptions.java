package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generic_graph.Direction;
import ca.ntro.core.graphs.generic_graph.SearchOptions;
import ca.ntro.core.graphs.generic_graph.SearchStrategy;
import ca.ntro.core.graphs.graph.GraphSearchOptions;

public class DirectedGraphSearchOptions extends GraphSearchOptions implements SearchOptions {
	
	private Direction[] directions = new Direction[] {Direction.FORWARD};

	protected Direction[] getDirections() {
		return directions;
	}

	protected void setDirections(Direction[] directions) {
		this.directions = directions;
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


	public DirectedGraphSearchOptions(Direction[] directions, int maxDistance) {
		super(maxDistance);
		setDirections(directions);
	}
	

	@Override
	public Direction[] directions() {
		return this.directions;
	}


}
