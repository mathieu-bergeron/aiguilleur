package ca.ntro.core.wrappers.result;

public interface Result<R extends Object> {

	R value();
	
	boolean hasValue();
	
	boolean hasException();
	
	Throwable exception();

	void throwException();
}
