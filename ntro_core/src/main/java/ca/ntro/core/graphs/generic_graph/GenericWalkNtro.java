package ca.ntro.core.graphs.generic_graph;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.identifyers.Key;

public class GenericWalkNtro<E extends GenericEdge> implements GenericWalk<E> {
	
	private List<E> steps = new ArrayList<>();

	@Override
	public int size() {
		return steps.size();
	}

	@Override
	public Key toKey() {
		throw new RuntimeException("TODO");
	}

	@Override
	public E step(int index) {
		return steps.get(index);
	}

}
