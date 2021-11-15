package ca.ntro.core.services;

import java.util.HashSet;
import java.util.Set;

public class ExceptionThrowerMock implements ExceptionThrower {
	
	private Set<String> thrown = new HashSet<>();
	private Throwable exception;

	@Override
	public void throwException(Throwable t) {
		thrown.add(t.getClass().getName());
		exception = t;
	}
	
	public boolean wasThrowned(Class<? extends Throwable> _class) {
		return thrown.contains(_class.getName());
	}

	public void clear() {
		thrown.clear();
	}

	@Override
	public void enterCatchingMode() {
	}

	@Override
	public void exitCatchingMode() {
		thrown.clear();
	}

	@Override
	public boolean hasException() {
		return exception != null;
	}

	@Override
	public Throwable exception() {
		return exception;
	}
}
