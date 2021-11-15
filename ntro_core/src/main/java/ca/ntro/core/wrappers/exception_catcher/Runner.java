package ca.ntro.core.wrappers.exception_catcher;

public interface Runner<R extends Object> {
	
	R run() throws Throwable;

}
