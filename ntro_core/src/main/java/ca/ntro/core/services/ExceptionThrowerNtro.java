package ca.ntro.core.services;

import ca.ntro.core.exceptions.Break;

public class ExceptionThrowerNtro implements ExceptionThrower {
	
	private boolean catchingMode = false;
	private Throwable exception = null;

	@Override
	public void throwException(Throwable t) {
		if(catchingMode) {

			exception = t;

		}else if(!(exception instanceof Break)){

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
