package ca.ntro.core.stream;

import java.util.List;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class StreamNtro<I extends Object> implements Stream<I> {
	
	protected abstract <R> void _reduce(ResultNtro<R> result, InternalReducer<I,R> reducer);

	@Override
	public boolean ifAll(GenericMatcher<I> matcher) {
		ResultNtro<Boolean> result = new ResultNtro<>(true);

		_reduce(result, (__, item) -> {
			try {

				if(!matcher.matches(item)) {
					result.registerValue(false);
					throw new Break();
				}
				
			}catch(Throwable t) {
				result.registerException(t);
			}
		});

		return result.value();
	}

	@Override
	public boolean ifSome(GenericMatcher<I> matcher) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void forEach(GenericVisitor<I> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public I findFirst(GenericMatcher<I> matcher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<I> findAll(GenericMatcher<I> matcher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<I> map(GenericMapper<I> mapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> R reduce(R initialValue, GenericReducer<I, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<I> collect() {
		// TODO Auto-generated method stub
		return null;
	}
}
