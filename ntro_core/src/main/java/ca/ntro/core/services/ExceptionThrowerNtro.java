package ca.ntro.core.services;

public class ExceptionThrowerNtro implements ExceptionThrower {

	@Override
	public void throwException(Throwable t) {
		throw new RuntimeException(t.getMessage());
	}
}
