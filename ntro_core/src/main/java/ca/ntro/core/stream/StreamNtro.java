package ca.ntro.core.stream;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class StreamNtro<I extends Object> implements Stream<I> {
	
	protected abstract <R> void _reduce(ResultNtro<R> result, _Reducer<I,R> _reducer);

	@Override
	public boolean ifAll(Matcher<I> matcher) {
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
	public boolean ifSome(Matcher<I> matcher) {
		ResultNtro<Boolean> result = new ResultNtro<>(false);

		_reduce(result, (__, item) -> {
			try {

				if(matcher.matches(item)) {
					result.registerValue(true);
					throw new Break();
				}
				
			}catch(Throwable t) {
				result.registerException(t);
			}
		});

		return result.value();
	}

	@Override
	public void forEach(Visitor<I> visitor) {
		ResultNtro<?> result = new ResultNtro<>();

		_reduce(result, (__, item) -> {
			try {

				visitor.visit(item);
				
			}catch(Throwable t) {
				result.registerException(t);
			}
		});
	}

	@Override
	public I findFirst(Matcher<I> matcher) {
		ResultNtro<I> result = new ResultNtro<>();

		_reduce(result, (__, item) -> {
			try {

				if(matcher.matches(item)) {
					result.registerValue(item);
					throw new Break();
				}
				
			}catch(Throwable t) {
				result.registerException(t);
			}
		});

		return result.value();
	}

	@Override
	public Stream<I> findAll(Matcher<I> matcher) {
		return new StreamNtro<I>() {
			@Override
			protected <R> void _reduce(ResultNtro<R> result, _Reducer<I, R> _reducer) {
				StreamNtro.this._reduce(result, (__, item) -> {
					try {

						if(matcher.matches(item)) {
							_reducer._reduce(result, item);
						}

					}catch(Throwable t) {
						result.registerException(t);
					}
				});
			}
		};
	}

	@Override
	public <A> Stream<A> map(Mapper<I,A> mapper) {
		return new StreamNtro<A>() {
			@Override
			protected <R> void _reduce(ResultNtro<R> result, _Reducer<A, R> _reducer) {
				StreamNtro.this._reduce(result, (__, item) -> {
					try {

						_reducer._reduce(result, mapper.map(item));

					}catch(Throwable t) {
						result.registerException(t);
					}
				});
			}
		};
	}

	@Override
	public <R> Result<R> reduce(R initialValue, Reducer<I, R> reducer) {
		ResultNtro<R> result = new ResultNtro<>(initialValue);

		_reduce(result, (__, item) -> {
			try {

				result.registerValue(reducer.reduce(result.value(), item).value());

			}catch(Throwable t) {
				result.registerException(t);
			}
		});

		return result;
	}

	@Override
	public List<I> collect() {
		List<I> result = new ArrayList<>();
		
		forEach(item -> {
			result.add(item);
		});

		return result;
	}
}
