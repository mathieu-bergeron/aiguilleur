package ca.ntro.jj.common;

import ca.ntro.jj.wrappers.ExceptionHandler;

public interface ExceptionDelayer<R extends Object> {

	R handleException(ExceptionHandler exceptionHandler);

}
