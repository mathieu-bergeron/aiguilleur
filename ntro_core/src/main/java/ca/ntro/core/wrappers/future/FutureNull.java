package ca.ntro.core.wrappers.future;

public class FutureNull<O extends Object> implements Future<O> {

	@Override
	public Future<O> handleValue(ValueHandler<O> resultHandler) {
		return this;
	}

	@Override
	public Future<O> handleException(ExceptionHandler exceptionHandler) {
		return this;
	}
}
