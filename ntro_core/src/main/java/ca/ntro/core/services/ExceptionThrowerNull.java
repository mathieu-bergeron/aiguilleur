package ca.ntro.core.services;

public class ExceptionThrowerNull implements ExceptionThrower {

	@Override
	public void throwException(Throwable t) {
	}

	@Override
	public void enterCatchingMode() {
	}

	@Override
	public void exitCatchingMode() {
	}

	@Override
	public boolean hasException() {
		return false;
	}

	@Override
	public Throwable exception() {
		return null;
	}

}
