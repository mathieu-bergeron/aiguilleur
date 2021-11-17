package ca.ntro.core.services;

import ca.ntro.core.reflection.ObjectGraph;
import ca.ntro.core.reflection.ObjectGraphNull;

public class ReflectionServiceNull implements ReflectionService {

	@Override
	public ObjectGraph objectGraph(Object o) {
		return new ObjectGraphNull();
	}

}
