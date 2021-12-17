package ca.ntro.core.wrappers.result;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.initialization.Ntro;

public class ResultNtro<R extends Object> implements Result<R> {
	
	private R value = null;
	private boolean hasValue = false;
	private Throwable exception = null;

	public ResultNtro() {
	}

	public ResultNtro(R value) {
		registerValue(value);
	}

	public void registerValue(R value) {
		this.value = value;
		this.hasValue = true;
	}

	public void registerException(Throwable exception) {
		this.exception = exception;
	}

	@Override
	public R value() {
		throwException();
		return value;
	}

	@Override
	public boolean hasValue() {
		return this.hasValue;
	}

	@Override
	public boolean hasException() {
		return this.exception != null;
	}

	@Override
	public Throwable exception() {
		return this.exception;
	}

	@Override
	public void throwException() {
		if(exception != null && !(exception instanceof Break)) {
			Ntro.exceptions().throwException(this.exception);
		}
	}
}
