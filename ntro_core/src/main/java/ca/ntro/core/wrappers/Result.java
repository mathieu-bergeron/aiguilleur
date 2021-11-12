package ca.ntro.core.wrappers;

public interface Result<R extends Object> {

	R get();

	void handleException(ExceptionHandler  exceptionHandler);
}
