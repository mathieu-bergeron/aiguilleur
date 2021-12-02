package ca.ntro.core.graphs;

import ca.ntro.core.wrappers.optionnal.Optionnal;

public interface SearchOptions<SO extends SearchOptions<SO>> {
	
	SearchStrategy searchStrategy();
	Direction[] directions();
	Optionnal<Integer> maxDistance(); 
	boolean sortEdgesByName();

	void setSearchStrategy(SearchStrategy searchStrategy);
	void setDirections(Direction[] directions);
	void setMaxDistance(int maxDistance);
	void setMaxDistance(Optionnal<Integer> maxDistance);
	void setSortEdgesByName(boolean sortEdgesByName);

	boolean containsDirection(Direction direction);
	
	SO createClone();
}
