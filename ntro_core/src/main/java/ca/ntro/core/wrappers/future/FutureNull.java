package ca.ntro.core.wrappers.future;

import ca.ntro.core.wrappers.result.Result;

public class FutureNull<O extends Object> implements Future<O> {

	@Override
	public Future<O> handleValue(ValueHandler<O> resultHandler) {
		return this;
	}

	@Override
	public Future<O> handleException(ExceptionHandler exceptionHandler) {
		return this;
	}

	@Override
	public Result<O> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<O> get(long maxDelay) {
		// TODO Auto-generated method stub
		return null;
	}
}
