package ca.ntro.core.services;

import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.reflection.object_graph.ObjectGraphNull;

public class ReflectionServiceNull implements ReflectionService {

	@Override
	public ObjectGraph objectGraph(Object o) {
		return new ObjectGraphNull();
	}

}
