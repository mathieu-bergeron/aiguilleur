package ca.ntro.core.services;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.exceptions.Break;

public class ExceptionThrowerMock implements ExceptionThrower {
	
	private Set<String> thrown = new HashSet<>();
	private Throwable lastException;

	@Override
	public void throwException(Throwable t) {
		if(!(t instanceof Break)) {
			thrown.add(t.getClass().getName());
			lastException = t;
		}
	}
	
	public boolean wasThrown(Class<? extends Throwable> _class) {
		return thrown.contains(_class.getName());
	}

	public boolean hasThrown() {
		return !thrown.isEmpty();
	}

	public void clear() {
		thrown.clear();
		lastException = null;
	}

	@Override
	public void enterCatchingMode() {
	}

	@Override
	public void exitCatchingMode() {
		clear();
	}

	@Override
	public boolean hasException() {
		return lastException != null;
	}

	@Override
	public Throwable exception() {
		return lastException;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if(hasException()) {
			builder.append(exception().getMessage());
		}
		
		return builder.toString();
	}

	public void throwLastException() throws Throwable {
		if(hasException()) {
			throw exception();
		}
		
	}
}
