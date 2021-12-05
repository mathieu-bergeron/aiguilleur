package ca.ntro.core.stream;

import java.util.List;

import ca.ntro.core.wrappers.result.Result;

public interface Stream<I extends Object> {

	boolean ifAll(GenericMatcher<I> matcher);

	boolean ifSome(GenericMatcher<I> matcher);

	void forEach(GenericVisitor<I> visitor);
	
	I findFirst(GenericMatcher<I> matcher);

	Stream<I> findAll(GenericMatcher<I> matcher);

	<R> Stream<R> map(GenericMapper<I,R> mapper);

	<R> Result<R> reduce(R initialValue, GenericReducer<I,R> reducer);

	List<I> collect();
}
