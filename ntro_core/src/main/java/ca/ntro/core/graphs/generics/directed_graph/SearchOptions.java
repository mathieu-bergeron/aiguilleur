package ca.ntro.core.graphs.generics.directed_graph;

import java.util.Set;

import ca.ntro.core.wrappers.optionnal.Optionnal;

public interface SearchOptions {

	SearchStrategy searchStrategy();
	Direction[] directions();
	Optionnal<Integer> maxDistance();
	boolean sortEdgesByName();
	
	Set<String> visitedNodes();
	Set<String> visitedEdges();

	void copyOptions(SearchOptions searchOptions);

}
