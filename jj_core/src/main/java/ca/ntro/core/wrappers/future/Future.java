package ca.ntro.core.wrappers.future;

import ca.ntro.core.exceptions.ExceptionCatcher;
import ca.ntro.core.wrappers.ExceptionHandler;

public interface Future<R extends Object> extends ExceptionCatcher<Future<R>> {
	
	Future<R> handleResult(ResultHandler<R> resultHandler);

	Future<R> handleException(ExceptionHandler  exceptionHandler);

}
