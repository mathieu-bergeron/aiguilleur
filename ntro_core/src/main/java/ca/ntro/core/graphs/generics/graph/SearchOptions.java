package ca.ntro.core.graphs.generics.graph;

public interface SearchOptions {

	void setSearchStrategy(SearchStrategy searchStrategy);
	void setMaxDistance(int maxDistance);
	void setSortEdgesByName(boolean sortEdgesByName);

	void copyOptions(InternalSearchOptions searchOptions);
	InternalSearchOptions internal();
}
