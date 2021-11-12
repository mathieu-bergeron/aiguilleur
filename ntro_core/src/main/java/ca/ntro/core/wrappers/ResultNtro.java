package ca.ntro.core.wrappers;

public class ResultNtro<R extends Object> implements Result<R> {
	
	private R value = null;
	private Throwable exception = null;
	
	private ResultNtro() {
	}

	public ResultNtro(R value) {
		this.value = value;
	}

	public void updateValue(R value) {
		this.value = value;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

	@Override
	public R get() {
		return value;
	}

	@Override
	public void handleException(ExceptionHandler exceptionHandler) {
		if(exception != null) {
			exceptionHandler.handle(exception);
		}
	}


}
