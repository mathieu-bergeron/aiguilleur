package ca.ntro.jj.wrappers.result;

public class ResultJj<R extends Object> implements Result<R> {
	
	private R result;
	private Throwable t;
	
	public ResultJj(R result) {
		this.result = result;
	}

	public ResultJj(Throwable t) {
		this.t = t;
	}

	@Override
	public R get() {
		return result;
	}

	@Override
	public void handleException(ExceptionHandler exceptionHandler) {
		exceptionHandler.handle(t);
	}
}
