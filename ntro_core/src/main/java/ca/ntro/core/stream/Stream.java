package ca.ntro.core.stream;


import java.util.List;

import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface Stream<I extends Object> {

	boolean ifAll(Matcher<I> matcher);

	boolean ifSome(Matcher<I> matcher);

	void forEach(Visitor<I> visitor);
	
	I findFirst(Matcher<I> matcher);

	Stream<I> findAll(Matcher<I> matcher);

	<R> Stream<R> map(Mapper<I,R> mapper);

	List<I> collect();

	<R> Result<R> reduceToResult(R initialValue, Reducer<I,R> reducer);

	<R> void reduceWithResult(ResultNtro<R> result, _Reducer<I,R> _reducer);

	<R,A> Stream<R> reduceToStream(StreamReducer<I,A,R> reducer);


}
