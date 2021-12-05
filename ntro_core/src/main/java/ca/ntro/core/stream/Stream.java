package ca.ntro.core.stream;

import java.util.List;

import ca.ntro.core.wrappers.result.Result;

public interface Stream<I extends Object> {

	boolean ifAll(Matcher<I> matcher);

	boolean ifSome(Matcher<I> matcher);

	void forEach(Visitor<I> visitor);
	
	I findFirst(Matcher<I> matcher);

	Stream<I> findAll(Matcher<I> matcher);

	<R> Stream<R> map(Mapper<I,R> mapper);

	<R> Result<R> reduce(R initialValue, Reducer<I,R> reducer);

	List<I> collect();
}
