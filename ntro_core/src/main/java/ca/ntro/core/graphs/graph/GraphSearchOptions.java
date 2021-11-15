package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generic_graph.Direction;
import ca.ntro.core.graphs.generic_graph.SearchOptions;
import ca.ntro.core.graphs.generic_graph.SearchStrategy;
import ca.ntro.core.wrappers.optionnal.Optionnal;

public class GraphSearchOptions implements SearchOptions {
	
	private SearchStrategy searchStrategy = SearchStrategy.BREADTH_FIRST_SEARCH;
	private Optionnal<Integer> maxDistance = Optionnal.none(Integer.class);
	
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

	@Override
	public SearchStrategy searchStrategy() {
		return searchStrategy;
	}

	@Override
	public Direction[] directions() {
		return new Direction[] {Direction.FORWARD};
	}

	@Override
	public Optionnal<Integer> maxDistance() {
		return maxDistance;
	}
}
