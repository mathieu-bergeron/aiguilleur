package ca.ntro.core.services;

import ca.ntro.core.reflection.ObjectGraph;
import ca.ntro.core.reflection.ObjectGraphJdk;

public class ReflectionServiceJdk implements ReflectionService {

	@Override
	public ObjectGraph objectGraph(Object o) {
		return new ObjectGraphJdk(o);
	}

}
