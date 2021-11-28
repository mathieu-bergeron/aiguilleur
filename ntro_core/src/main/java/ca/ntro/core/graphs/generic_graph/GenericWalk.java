package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.identifyers.Key;

public interface GenericWalk<E extends GenericEdge> {

	int size();
	
	Key toKey();
	
	E step(int index);

}
