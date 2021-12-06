package ca.ntro.core.graphs.generics.directed_graph;

public class      SearchOptionsBuilderNtro

       implements SearchOptionsBuilder {
	
	private SearchOptionsNtro searchOptions = new SearchOptionsNtro();

	public SearchOptionsBuilderNtro() {
	}

	public SearchOptionsBuilderNtro(SearchOptions options) {
			setSearchOptions(new SearchOptionsNtro());
			copyOptions(options);
	}

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
		searchOptions.copyOptions(searchOptions);
	}

	@Override
	public SearchOptionsNtro internal() {
		return getSearchOptions();
	}
}