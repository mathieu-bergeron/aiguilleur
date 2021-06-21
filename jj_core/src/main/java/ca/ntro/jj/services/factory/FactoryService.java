package ca.ntro.jj.services.factory;

import java.util.List;

import ca.ntro.jj.services.Service;

public class FactoryService implements Service {

	public <R extends Object> R newInstance(Class<R> _class) throws InstantiationException, IllegalAccessException {
		return _class.newInstance();
	}

	public <R extends Object> R newInstance(Class<R> _class, List<Object> arguments) throws InstantiationException, IllegalAccessException {
		// FIXME: how to get constructor with arguments in JSweet?
		return _class.newInstance();
	}

}
