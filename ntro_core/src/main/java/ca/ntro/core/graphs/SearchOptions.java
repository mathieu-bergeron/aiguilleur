package ca.ntro.core.graphs;

import ca.ntro.core.wrappers.optionnal.Optionnal;

public interface SearchOptions {
	
	SearchStrategy searchStrategy();
	Direction[] directions();
	Optionnal<Integer> maxDistance();

	boolean containsDirection(Direction direction);

}
