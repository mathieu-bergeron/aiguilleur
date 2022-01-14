package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.identifyers.Key;
import ca.ntro.core.stream.Stream;

public interface GenericWalk<E extends GenericStep, W extends GenericWalk<E,W>> {

	WalkId id();

	boolean isEmpty();

	int size();
	
	Key toKey();
	
	E get(int index);
	
	Stream<E> edges();

	void add(E edge);
	
	W subWalk(int fromIndex);
	

}
