package ca.ntro.core.stream;

public interface _Reducer<I extends Object, R extends Object> {
	
	void _reduce(I item) throws Throwable;

}
