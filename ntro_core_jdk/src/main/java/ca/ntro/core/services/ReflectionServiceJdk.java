package ca.ntro.core.services;

import ca.ntro.core.reflection.ObjectGraphJdk;
import ca.ntro.core.reflection.object_graph.ObjectGraph;

public class ReflectionServiceJdk implements ReflectionService {

	@Override
	public ObjectGraph objectGraph(Object o) {
		return new ObjectGraphJdk(o);
	}

}
