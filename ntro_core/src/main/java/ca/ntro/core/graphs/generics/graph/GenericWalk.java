package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.identifyers.Key;

public interface GenericWalk<E extends GenericStep, W extends GenericWalk<E,W>> {

	boolean isEmpty();

	int size();
	
	Key toKey();
	
	E get(int index);

	void add(E edge);
	
	W subWalk(int fromIndex);

}
