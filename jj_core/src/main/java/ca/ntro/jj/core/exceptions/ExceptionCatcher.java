package ca.ntro.jj.core.exceptions;

import ca.ntro.jj.wrappers.ExceptionHandler;

public interface ExceptionCatcher<R extends Object> {

	R handleException(ExceptionHandler exceptionHandler);

}
