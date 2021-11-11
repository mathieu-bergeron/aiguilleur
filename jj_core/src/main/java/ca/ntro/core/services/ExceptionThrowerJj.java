package ca.ntro.core.services;

public class ExceptionThrowerJj implements ExceptionThrower {

	@Override
	public void throwException(Throwable t) {
		throw new RuntimeException(t.getMessage());
	}
}
