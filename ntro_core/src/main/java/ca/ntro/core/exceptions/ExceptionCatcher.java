package ca.ntro.core.exceptions;

import ca.ntro.core.wrappers.ExceptionHandler;

public interface ExceptionCatcher<R extends Object> {

	R handleException(ExceptionHandler exceptionHandler);

}
