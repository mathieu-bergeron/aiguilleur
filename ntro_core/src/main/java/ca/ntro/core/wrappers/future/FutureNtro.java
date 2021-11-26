package ca.ntro.core.wrappers.future;

public class FutureNtro<O extends Object> implements Future<O> {
	
	private enum State {
		VALUE, NO_VALUE;
	}
	
	private State state = State.NO_VALUE;

	private O value = null;
	private Throwable exception = null;
	
	private ValueHandler<O> resultHandler = null;
	private ExceptionHandler exceptionHandler = null;
	
	public void registerValue(O value) {
		this.value = value;
		state = State.VALUE;
		
		if(resultHandler != null) {
			resultHandler.handle(value);
		}
	}
	
	public void registerException(Throwable exception) {
		this.exception = exception;

		if(exceptionHandler != null && exception != null) {
			exceptionHandler.handle(exception);
		}
	}

	@Override
	public Future<O> handleValue(ValueHandler<O> resultHandler) {
		this.resultHandler = resultHandler;

		if(state == State.VALUE && resultHandler != null) {
			resultHandler.handle(value);
		}

		return this;
	}

	@Override
	public Future<O> handleException(ExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;

		if(exception != null && exceptionHandler != null) {
			exceptionHandler.handle(exception);
		}

		return this;
	}

}
