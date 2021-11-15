package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.wrappers.optionnal.Optionnal;

public interface SearchOptions {
	
	SearchStrategy searchStrategy();
	Direction[] directions();
	Optionnal<Integer> maxDistance();

}
