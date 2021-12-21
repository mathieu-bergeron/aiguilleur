package ca.ntro.core.wrappers.future;

import java.util.concurrent.TimeoutException;

import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class FutureNtro<O extends Object> implements Future<O> {
	
	public static final long FUTURE_GET_SLEEP_TIME_MILLIS = 200;
	public static final long MIN_DELAY_MILLIS = FUTURE_GET_SLEEP_TIME_MILLIS * 5 / 4;
	public static final long FUTURE_GET_DEFAULT_MAX_DELAY_MILLIS = 30 * 1000;
	
	private boolean hasValue = false;
	private O value = null;
	private Throwable exception = null;
	
	private ValueHandler<O> resultHandler = null;
	private ExceptionHandler exceptionHandler = null;
	
	public void registerValue(O value) {
		this.value = value;
		this.hasValue = true;
		
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

		if(hasValue() && resultHandler != null) {
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
	
	public boolean hasException() {
		return exception != null;
	}

	public boolean hasValue() {
		return hasValue;
	}

	@Override
	public Result<O> get() {
		return get(FUTURE_GET_DEFAULT_MAX_DELAY_MILLIS);
	}

	@Override
	public Result<O> get(long maxDelayMillis) {
		ResultNtro<O> result = new ResultNtro<>();
		
		long start = Ntro.time().nowMillis();
		maxDelayMillis = maxDelayMillis >= MIN_DELAY_MILLIS ? maxDelayMillis : MIN_DELAY_MILLIS;
		long delayMillis = 0;
		
		while(delayMillis < maxDelayMillis) {
			
			if(hasValue() || hasException()) {
				break;
			}
			
			Ntro.time().sleep(FUTURE_GET_SLEEP_TIME_MILLIS);
			
			delayMillis = Ntro.time().nowMillis() - start;
		}
		
		if(hasException()) {

			result.registerException(exception);
			
		} else if(delayMillis >= maxDelayMillis) {

			result.registerException(new TimeoutException());
			
		}else if(hasValue()) {

			result.registerValue(value);
		}

		return result;
	}

}
