package ca.ntro.core.stream;

import ca.ntro.core.wrappers.result.ResultNtro;

public interface _Stream<I extends Object> {

	<R> void _reduce(ResultNtro<R> result, _Reducer<I,R> _reducer);
}
