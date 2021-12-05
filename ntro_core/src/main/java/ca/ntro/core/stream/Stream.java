package ca.ntro.core.stream;

import java.util.List;

public interface Stream<I extends Object> {

	boolean ifAll(GenericMatcher<I> matcher);

	boolean ifSome(GenericMatcher<I> matcher);

	void forEach(GenericVisitor<I> visitor);
	
	I findFirst(GenericMatcher<I> matcher);

	Stream<I> findAll(GenericMatcher<I> matcher);

	Stream<I> map(GenericMapper<I> mapper);

	<R> R reduce(R initialValue, GenericReducer<I,R> reducer);

	List<I> collect();
}
