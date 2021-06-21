package ca.ntro.jj.services.factory;

import ca.ntro.jj.services.Service;

public class FactoryService implements Service {

	public <R extends Object> R newInstance(Class<R> _class) throws InstantiationException, IllegalAccessException {
		return _class.newInstance();
	}

}
