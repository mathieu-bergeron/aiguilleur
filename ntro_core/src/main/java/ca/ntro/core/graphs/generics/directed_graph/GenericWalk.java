package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.identifyers.Key;

public interface GenericWalk<E extends GenericEdge> {

	boolean isEmpty();

	int size();
	
	Key toKey();
	
	E get(int index);

	void add(E edge);
	
	GenericWalk<E> subWalk(int fromIndex);

}
