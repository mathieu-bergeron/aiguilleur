package ca.ntro.jj.identifyers;

import ca.ntro.jj.initialization.Service;

public class ServiceIdJj<S extends Service<S>> extends ClassIdJj implements ServiceId<S> {

	public ServiceIdJj(Class<?> _class) {
		super(_class);
	}
}
