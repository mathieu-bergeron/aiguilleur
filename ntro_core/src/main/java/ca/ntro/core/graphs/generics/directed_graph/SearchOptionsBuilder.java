package ca.ntro.core.graphs.generics.directed_graph;

public interface SearchOptionsBuilder {

	void setSearchStrategy(SearchStrategy searchStrategy);
	void setMaxDistance(int maxDistance);
	void setSortEdgesByName(boolean sortEdgesByName);

	void copyOptions(SearchOptions searchOptions);
	SearchOptions internal();
}
