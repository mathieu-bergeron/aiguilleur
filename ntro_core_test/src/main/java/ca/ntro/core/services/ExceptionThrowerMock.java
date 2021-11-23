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
	
	public boolean wasThrown(Class<? extends Throwable> _class) {
		return thrown.contains(_class.getName());
	}

	public boolean hasThrown() {
		return !thrown.isEmpty();
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if(hasException()) {
			builder.append(exception().getMessage());
		}
		
		return builder.toString();
	}

	public void throwException() throws Throwable {
		if(hasException()) {
			throw exception();
		}
		
	}
}
