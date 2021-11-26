package ca.ntro.core.wrappers.future;

public interface ValueHandler<R extends Object> {
	
	void handle(R result);

}
