package ca.ntro.core.functional;

import ca.ntro.core.wrappers.result.Result;

public interface GenericReducer<V extends Object, R extends Object> {
	
	Result<R> reduce(R accumulator, V value);

}
