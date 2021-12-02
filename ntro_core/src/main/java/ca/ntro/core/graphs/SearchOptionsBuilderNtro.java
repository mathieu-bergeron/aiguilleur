package ca.ntro.core.graphs;

public class      SearchOptionsBuilderNtro

       implements SearchOptionsBuilder {
	
	private SearchOptionsNtro searchOptions = new SearchOptionsNtro();

	public SearchOptionsNtro getSearchOptions() {
		return searchOptions;
	}

	public void setSearchOptions(SearchOptionsNtro searchOptions) {
		this.searchOptions = searchOptions;
	}

	@Override
	public void setSearchStrategy(SearchStrategy searchStrategy) {
		getSearchOptions().setSearchStrategy(searchStrategy);
	}

	@Override
	public void setMaxDistance(int maxDistance) {
		getSearchOptions().setMaxDistance(maxDistance);
	}

	@Override
	public void setSortEdgesByName(boolean sortEdgesByName) {
		getSearchOptions().setSortEdgesByName(sortEdgesByName);
	}

	@Override
	public void copyOptions(SearchOptions searchOptions) {
		getSearchOptions().setSearchStrategy(searchOptions.searchStrategy());
		getSearchOptions().setMaxDistance(searchOptions.maxDistance());
		getSearchOptions().setDirections(searchOptions.directions());
		getSearchOptions().setSortEdgesByName(searchOptions.sortEdgesByName());
	}

	@Override
	public SearchOptionsNtro toSearchOptions() {
		return getSearchOptions();
	}
}
