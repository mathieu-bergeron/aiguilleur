package ca.ntro.core.stream;

public interface StreamReducer<I extends Object, M extends Object, R extends Object> {
	
	Stream<R> reduce(Reducer<M,I> reducer, I item) throws Throwable;

}
