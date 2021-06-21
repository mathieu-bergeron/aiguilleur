package ca.ntro.jj.wrappers.future;

public interface ResultHandler<R extends Object> {
	
	void handle(R result);

}
