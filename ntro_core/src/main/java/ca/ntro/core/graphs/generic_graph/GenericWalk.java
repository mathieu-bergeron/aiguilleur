package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.identifyers.Key;

public interface GenericWalk<S extends GenericStep> {

	int size();
	
	Key toKey();
	
	S step(int index);

}
