package ca.ntro.jj.services;

import ca.ntro.jj.initialization.Service;

public abstract class Tracer extends Service<Tracer> {

	public abstract void trace(Object calledClassOrObject, Object[] arguments);

}
