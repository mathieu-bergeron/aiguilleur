package ca.ntro.core.stream;

public interface StreamReducer<I extends Object, R extends Object> {

	void reduce(I item, Visitor<R> visitor) throws Throwable;

}
