package ca.ntro.core.graphs;

import ca.ntro.core.wrappers.optionnal.Optionnal;

public class SearchOptionsNtro implements SearchOptions {
	
	private SearchStrategy searchStrategy = SearchStrategy.DEPTH_FIRST_SEARCH;
	private Optionnal<Integer> maxDistance = Optionnal.none(Integer.class);
	private Direction[] directions = defaultDirections();
	private Direction walkDirection = directions[0];
	
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

	public SearchOptionsNtro() {
	}

	public SearchOptionsNtro(SearchStrategy searchStrategy) {
		setSearchStrategy(searchStrategy);
	}

	public SearchOptionsNtro(SearchStrategy searchStrategy, int maxDistance) {
		setSearchStrategy(searchStrategy);
		setMaxDistance(maxDistance);
	}

	public SearchOptionsNtro(int maxDistance) {
		setMaxDistance(maxDistance);
	}

	public SearchOptionsNtro(Direction[] directions) {
		setDirections(directions);
	}

	public SearchOptionsNtro(Direction[] directions, int maxDistance) {
		setDirections(directions);
		setMaxDistance(maxDistance);
	}

	public SearchOptionsNtro(SearchStrategy searchStrategy, Direction[] directions, int maxDistance) {
		setSearchStrategy(searchStrategy);
		setDirections(directions);
		setMaxDistance(maxDistance);
	}

	@Override
	public SearchStrategy searchStrategy() {
		return getSearchStrategy();
	}

	@Override
	public Direction[] searchDirections() {
		return getDirections();
	}

	@Override
	public Optionnal<Integer> maxDistance() {
		return getMaxDistance();
	}
	
	protected Direction[] defaultDirections() {
		return new Direction[] {Direction.FORWARD};
	}

	@Override
	public Direction walkDirection() {
		return walkDirection;
	}
}
