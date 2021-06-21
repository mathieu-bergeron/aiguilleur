package ca.ntro.jj.wrappers.future;

import ca.ntro.jj.common.ExceptionDelayer;
import ca.ntro.jj.wrappers.result.ExceptionHandler;

public interface Future<R extends Object> extends ExceptionDelayer {
	
	R getNow() throws NotYetAvailable;

	void handleResult(ResultHandler<R> resultHandler);

	void handleException(ExceptionHandler  exceptionHandler);

}
