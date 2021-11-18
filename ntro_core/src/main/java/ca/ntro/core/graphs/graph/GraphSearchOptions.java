package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.SearchStrategy;
import ca.ntro.core.wrappers.optionnal.Optionnal;

public class GraphSearchOptions implements SearchOptions {
	
	private SearchStrategy searchStrategy = SearchStrategy.BREADTH_FIRST_SEARCH;
	private Optionnal<Integer> maxDistance = Optionnal.none(Integer.class);
	private Direction[] directions = defaultDirections();
	
	protected SearchStrategy getSearchStrategy() {
		return searchStrategy;
	}

	protected void setSearchStrategy(SearchStrategy searchStrategy) {
		this.searchStrategy = searchStrategy;
	}

	protected Optionnal<Integer> getMaxDistance() {
		return maxDistance;
	}

	protected void setMaxDistance(Optionnal<Integer> maxDistance) {
		this.maxDistance = maxDistance;
	}

	protected void setMaxDistance(int maxDistance) {
		this.maxDistance = Optionnal.fromValue(maxDistance);
	}

	protected Direction[] getDirections() {
		return directions;
	}

	protected void setDirections(Direction[] directions) {
		this.directions = directions;
	}

	public GraphSearchOptions() {
	}

	public GraphSearchOptions(SearchStrategy searchStrategy) {
		setSearchStrategy(searchStrategy);
	}

	public GraphSearchOptions(SearchStrategy searchStrategy, int maxDistance) {
		setSearchStrategy(searchStrategy);
		setMaxDistance(maxDistance);
	}

	public GraphSearchOptions(int maxDistance) {
		setMaxDistance(maxDistance);
	}

	public GraphSearchOptions(Direction[] directions) {
		setDirections(directions);
	}

	@Override
	public SearchStrategy searchStrategy() {
		return getSearchStrategy();
	}

	@Override
	public Direction[] directions() {
		return getDirections();
	}

	@Override
	public Optionnal<Integer> maxDistance() {
		return getMaxDistance();
	}
	
	protected Direction[] defaultDirections() {
		return new Direction[] {Direction.FORWARD};
	}
}
