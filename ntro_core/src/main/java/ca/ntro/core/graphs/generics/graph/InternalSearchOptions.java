package ca.ntro.core.graphs.generics.graph;

import java.util.Set;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.wrappers.optionnal.Optionnal;

public interface InternalSearchOptions {

	SearchStrategy searchStrategy();
	Direction[] directions();
	Optionnal<Integer> maxDistance();
	boolean sortEdgesByName();
	
	Set<String> visitedNodes();
	Set<String> visitedEdges();

	void copyOptions(InternalSearchOptions searchOptions);

}
