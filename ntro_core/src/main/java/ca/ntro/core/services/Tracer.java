package ca.ntro.core.services;

import ca.ntro.core.initialization.Service;

public abstract class Tracer extends Service<Tracer> {

	public abstract void trace(Object calledClassOrObject, Object[] arguments);

}
