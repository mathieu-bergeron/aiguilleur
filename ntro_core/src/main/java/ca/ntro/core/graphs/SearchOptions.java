package ca.ntro.core.graphs;

import ca.ntro.core.wrappers.optionnal.Optionnal;

public interface SearchOptions {
	
	SearchStrategy searchStrategy();
	Direction[] searchDirections();
	Direction walkDirection();
	Optionnal<Integer> maxDistance();

}
