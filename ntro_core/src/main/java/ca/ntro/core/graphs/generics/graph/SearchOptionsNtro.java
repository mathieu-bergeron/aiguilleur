package ca.ntro.core.graphs.generics.graph;

public class      SearchOptionsNtro

       implements SearchOptions {
	
	private InternalSearchOptionsNtro internalSearchOptions = new InternalSearchOptionsNtro();

	public InternalSearchOptionsNtro getInternalSearchOptions() {
		return internalSearchOptions;
	}

	public void setInternalSearchOptions(InternalSearchOptionsNtro internalSearchOptions) {
		this.internalSearchOptions = internalSearchOptions;
	}

	public SearchOptionsNtro() {
	}

	public SearchOptionsNtro(InternalSearchOptions options) {
			setInternalSearchOptions(new InternalSearchOptionsNtro());
			copyOptions(options);
	}

	@Override
	public void setSearchStrategy(SearchStrategy searchStrategy) {
		getInternalSearchOptions().setSearchStrategy(searchStrategy);
	}

	@Override
	public void setMaxDistance(int maxDistance) {
		getInternalSearchOptions().setMaxDistance(maxDistance);
	}

	@Override
	public void setSortEdgesByName(boolean sortEdgesByName) {
		getInternalSearchOptions().setSortEdgesByName(sortEdgesByName);
	}

	@Override
	public void copyOptions(InternalSearchOptions searchOptions) {
		getInternalSearchOptions().copyOptions(searchOptions);
	}

	@Override
	public InternalSearchOptionsNtro internal() {
		return getInternalSearchOptions();
	}
}
