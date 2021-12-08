package ca.ntro.core.stream;

import ca.ntro.core.wrappers.result.ResultNtro;

public interface StreamReducer<I extends Object, R extends Object> {

	void reduce(ResultNtro<R> result, Reducer<I,R> reducer, I item) throws Throwable;

}
