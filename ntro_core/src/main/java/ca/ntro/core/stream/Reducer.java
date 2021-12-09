package ca.ntro.core.stream;

import ca.ntro.core.wrappers.result.ResultNtro;

public interface Reducer<I extends Object, R extends Object> {
	
	void reduce(ResultNtro<R> result, I item) throws Throwable;

}
