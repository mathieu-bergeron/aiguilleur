package ca.ntro.core.services;

import java.util.HashSet;
import java.util.Set;

public class ExceptionThrowerMock implements ExceptionThrower {
	
	private Set<String> thrown = new HashSet<>();

	@Override
	public void throwException(Throwable t) {
		thrown.add(t.getClass().getName());
	}
	
	public boolean wasThrowned(Class<? extends Throwable> _class) {
		return thrown.contains(_class.getName());
	}

	public void clear() {
		thrown.clear();
	}
}
