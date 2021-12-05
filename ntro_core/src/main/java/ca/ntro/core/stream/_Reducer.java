package ca.ntro.core.stream;

import ca.ntro.core.wrappers.result.ResultNtro;

public interface _Reducer<I extends Object, R extends Object> {
	
	void _reduce(ResultNtro<R> result, I item) throws Throwable;

}
