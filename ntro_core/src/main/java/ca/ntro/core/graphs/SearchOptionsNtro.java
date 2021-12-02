package ca.ntro.core.graphs;

import ca.ntro.core.wrappers.optionnal.Optionnal;

public class SearchOptionsNtro implements SearchOptions {

	private SearchStrategy searchStrategy = SearchStrategy.DEPTH_FIRST_SEARCH;
	private Optionnal<Integer> maxDistance = Optionnal.none(Integer.class);
	private Direction[] directions = new Direction[] {Direction.BACKWARD, Direction.FORWARD};
	private boolean sortEdgesByName = false;

	public SearchStrategy getSearchStrategy() {
		return searchStrategy;
	}

	public void setSearchStrategy(SearchStrategy searchStrategy) {
		this.searchStrategy = searchStrategy;
	}

	public Optionnal<Integer> getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(Optionnal<Integer> maxDistance) {
		this.maxDistance = maxDistance;
	}

	public void setMaxDistance(int maxDistance) {
		this.maxDistance = Optionnal.fromValue(maxDistance);
	}

	public Direction[] getDirections() {
		return directions;
	}

	public void setDirections(Direction[] directions) {
		this.directions = directions;
	}

	public boolean getSortEdgesByName() {
		return sortEdgesByName;
	}

	public void setSortEdgesByName(boolean sortEdgesByName) {
		this.sortEdgesByName = sortEdgesByName;
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

	@Override
	public boolean sortEdgesByName() {
		return getSortEdgesByName();
	}

	@Override
	public void copyOptions(SearchOptions searchOptions) {
		setSearchStrategy(searchOptions.searchStrategy());
		setMaxDistance(searchOptions.maxDistance());
		setDirections(searchOptions.directions());
		setSortEdgesByName(searchOptions.sortEdgesByName());
	}
}
