package ca.ntro.jj.common;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.jj.wrappers.ExceptionHandler;

public class ExceptionDelayerJj<R extends Object> implements ExceptionDelayer<R> {
	
	private List<Throwable> exceptions = new ArrayList<>();

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
