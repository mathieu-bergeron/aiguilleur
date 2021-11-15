package ca.ntro.core.wrappers.future;

public class FutureNull<O extends Object> implements Future<O> {

	@Override
	public Future<O> handleResult(ResultHandler<O> resultHandler) {
		return this;
	}

	@Override
	public Future<O> handleException(ExceptionHandler exceptionHandler) {
		return this;
	}
}
