package ca.ntro.jj.services;

public class ExceptionThrowerJj implements ExceptionThrower {

	@Override
	public void throwException(Throwable t) {
		t.printStackTrace();
		System.exit(0);
	}
}
