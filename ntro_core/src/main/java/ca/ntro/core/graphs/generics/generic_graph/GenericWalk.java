package ca.ntro.core.graphs.generics.generic_graph;

import ca.ntro.core.identifyers.Key;

public interface GenericWalk<E extends GenericStep> {

	boolean isEmpty();

	int size();
	
	Key toKey();
	
	E get(int index);

	void add(E edge);
	
	GenericWalk<E> subWalk(int fromIndex);

}
