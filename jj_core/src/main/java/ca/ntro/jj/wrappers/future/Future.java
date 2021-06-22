package ca.ntro.jj.wrappers.future;

import ca.ntro.jj.common.exceptions.ExceptionCatcher;
import ca.ntro.jj.wrappers.ExceptionHandler;

public interface Future<R extends Object> extends ExceptionCatcher<Future<R>> {
	
	Future<R> handleResult(ResultHandler<R> resultHandler);

	Future<R> handleException(ExceptionHandler  exceptionHandler);

}
