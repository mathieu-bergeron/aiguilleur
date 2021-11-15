package ca.ntro.core.wrappers.exception_catcher;

import ca.ntro.core.wrappers.result.Result;

public interface ExceptionCatcher {
	
	<R extends Object> Result<R> catchExceptions(Runnable task);

}
