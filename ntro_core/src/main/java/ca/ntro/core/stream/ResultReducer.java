package ca.ntro.core.stream;

public interface ResultReducer<I extends Object, R extends Object> {
	
	R reduce(R accumulator, I item) throws Throwable;

}
