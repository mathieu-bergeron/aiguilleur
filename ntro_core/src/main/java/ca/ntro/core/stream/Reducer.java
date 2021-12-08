package ca.ntro.core.stream;

public interface Reducer<I extends Object, R extends Object> {
	
	R reduce(R accumulator, I item) throws Throwable;

}
