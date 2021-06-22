package ca.ntro.jj.common.exceptions;

import ca.ntro.jj.wrappers.ExceptionHandler;

public interface ExceptionCatcher<R extends Object> {

	R handleException(ExceptionHandler exceptionHandler);

}
