package ca.ntro.core.graphs.generics.graph;

public class      SearchOptionsNtro

       implements SearchOptions {
	
	private InternalSearchOptionsNtro searchOptions = new InternalSearchOptionsNtro();

	public SearchOptionsNtro() {
	}

	public SearchOptionsNtro(InternalSearchOptions options) {
			setSearchOptions(new InternalSearchOptionsNtro());
			copyOptions(options);
	}

	public InternalSearchOptionsNtro getSearchOptions() {
		return searchOptions;
	}

	public void setSearchOptions(InternalSearchOptionsNtro searchOptions) {
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
	public void copyOptions(InternalSearchOptions searchOptions) {
		searchOptions.copyOptions(searchOptions);
	}

	@Override
	public InternalSearchOptionsNtro internal() {
		return getSearchOptions();
	}
}
