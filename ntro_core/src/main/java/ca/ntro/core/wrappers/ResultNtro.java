package ca.ntro.core.wrappers;

public class ResultNtro<R extends Object> implements Result<R> {
	
	private R value = null;
	private boolean hasValue = false;
	private Throwable exception = null;
	
	private ResultNtro() {
	}

	public ResultNtro(R value) {
		this.value = value;
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
}
