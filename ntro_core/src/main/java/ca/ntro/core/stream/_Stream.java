package ca.ntro.core.stream;

import java.util.List;

import ca.ntro.core.wrappers.result.Result;

public interface _Stream<I extends Object> {

	boolean ifAll(Matcher<I> matcher);

	boolean ifSome(Matcher<I> matcher);

	void forEach(Visitor<I> visitor);
	
	I findFirst(Matcher<I> matcher);

	_Stream<I> findAll(Matcher<I> matcher);

	<R> _Stream<R> map(Mapper<I,R> mapper);

	<R> Result<R> reduce(R initialValue, _Reducer<I,R> _reducer);

	List<I> collect();
}
