package ca.ntro.core.wrappers;

public interface Result<R extends Object> {

	R value();
	
	boolean hasValue();
	
	boolean hasException();
	
	Throwable exception();
}
