package ca.ntro.jj.services;

public class ExceptionThrowerJj implements ExceptionThrower {

	@Override
	public void throwException(Throwable t) {
		throw new RuntimeException(t.getMessage());
	}
}
