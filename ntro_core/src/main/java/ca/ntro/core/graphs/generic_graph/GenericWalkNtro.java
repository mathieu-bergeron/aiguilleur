package ca.ntro.core.graphs.generic_graph;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.identifyers.Key;

public class GenericWalkNtro<S extends GenericStep> implements GenericWalk<S> {
	
	private List<S> steps = new ArrayList<>();

	@Override
	public int size() {
		return steps.size();
	}

	@Override
	public Key toKey() {
		throw new RuntimeException("TODO");
	}

	@Override
	public S step(int index) {
		return steps.get(index);
	}

}
