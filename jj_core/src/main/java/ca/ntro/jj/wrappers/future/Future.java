package ca.ntro.jj.wrappers.future;

import ca.ntro.jj.common.ExceptionDelayer;
import ca.ntro.jj.wrappers.ExceptionHandler;

public interface Future<R extends Object> extends ExceptionDelayer<Future<R>> {
	
	Future<R> handleResult(ResultHandler<R> resultHandler);

	Future<R> handleException(ExceptionHandler  exceptionHandler);

}
