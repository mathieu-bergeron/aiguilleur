package ca.ntro.core.wrappers;

public class ResultNtro<R extends Object> implements Result<R> {
	
	private R value = null;
	private Throwable exception = null;
	
	private ResultNtro() {
	}
	
	public ResultNtro(R value) {
		this.value = value;
	}
	
	public static <R extends Object> Result<R> fromException(Throwable exception){
		ResultNtro<R> result = new ResultNtro<R>();
		
		result.exception = exception;

		return result;
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
