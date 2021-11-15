package ca.ntro.core.exceptions;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.wrappers.future.ExceptionHandler;

public class ExceptionCatcherNtro<R extends Object> implements ExceptionCatcher<R> {
	
	private List<Throwable> exceptions = new ArrayList<>();

	@SuppressWarnings("unchecked")
	@Override
	public R handleException(ExceptionHandler exceptionHandler) {
		for(Throwable t : exceptions) {
			exceptionHandler.handle(t);
		}
		
		exceptions.clear();

		return (R) this;
	}
	
	protected void memorizeException(Throwable t) {
		exceptions.add(t);
	}

}
