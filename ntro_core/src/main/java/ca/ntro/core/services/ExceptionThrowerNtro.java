package ca.ntro.core.services;

public class ExceptionThrowerNtro implements ExceptionThrower {
	
	private boolean catchingMode = false;
	private Throwable exception = null;

	@Override
	public void throwException(Throwable t) {
		if(catchingMode) {

			exception = t;

		}else {

			throw new RuntimeException(t);
		}
	}

	@Override
	public void enterCatchingMode() {
		exception = null;
		catchingMode = true;
	}

	@Override
	public void exitCatchingMode() {
		catchingMode = false;
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
