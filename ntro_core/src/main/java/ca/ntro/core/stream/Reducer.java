package ca.ntro.core.stream;

import ca.ntro.core.wrappers.result.Result;

public interface Reducer<I extends Object, R extends Object> {
	
	Result<R> reduce(R accumulator, I item) throws Throwable;

}
