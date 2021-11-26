package ca.ntro.core.wrappers.future;

import ca.ntro.core.exceptions.ExceptionCatcher;

public interface Future<R extends Object> extends ExceptionCatcher<Future<R>> {
	
	Future<R> handleValue(ValueHandler<R> valueHandler);

	Future<R> handleException(ExceptionHandler  exceptionHandler);

}
