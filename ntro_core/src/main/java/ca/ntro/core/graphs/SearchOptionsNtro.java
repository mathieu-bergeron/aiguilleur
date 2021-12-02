package ca.ntro.core.graphs;

import ca.ntro.core.initialization.Factory;
import ca.ntro.core.wrappers.optionnal.Optionnal;

public class SearchOptionsNtro<SO extends SearchOptions<SO>> implements SearchOptions<SO> {
	
	private SearchStrategy searchStrategy = SearchStrategy.DEPTH_FIRST_SEARCH;
	private Optionnal<Integer> maxDistance = Optionnal.none(Integer.class);
	private Direction[] directions = defaultDirections();
	private boolean sortEdgesByName = defaultSortByEdgeNames();
	
	protected SearchStrategy getSearchStrategy() {
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

	public SearchOptionsNtro() {
	}

	protected Direction[] defaultDirections() {
		return new Direction[] {Direction.FORWARD};
	}

	protected boolean defaultSortByEdgeNames() {
		return false;
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
	public boolean containsDirection(Direction direction) {
		boolean contains = false;
		for(Direction candidate : directions()) {
			if(candidate == direction) {
				contains = true;
				break;
			}
		}
		
		return contains;
	}

	@Override
	public boolean sortEdgesByName() {
		return getSortEdgesByName();
	}

	@SuppressWarnings("unchecked")
	@Override
	public SO createClone() {
		SO clone = (SO) Factory.newInstance(this.getClass());

		clone.setDirections(directions());
		clone.setSearchStrategy(searchStrategy());
		clone.setMaxDistance(maxDistance());
		clone.setSortEdgesByName(sortEdgesByName());

		return clone;
	}
}
