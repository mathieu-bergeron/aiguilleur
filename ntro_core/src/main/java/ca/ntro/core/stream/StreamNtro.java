package ca.ntro.core.stream;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class StreamNtro<I extends Object> 

       implements     Stream<I> {
	
	@Override
	public abstract <R> void applyReducer(ResultNtro<R> result, Reducer<I,R> _reducer);

	@Override
	public boolean ifAll(Matcher<I> matcher) {
		ResultNtro<Boolean> result = new ResultNtro<>(true);

		applyReducer(result, (__, item) -> {
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

		applyReducer(result, (__,item) -> {
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

		applyReducer(result, (__,item) -> {
			try {

				visitor.visit(item);
				
			}catch(Throwable t) {
				result.registerException(t);
			}
		});
		
		result.throwException();
	}

	@Override
	public I findFirst(Matcher<I> matcher) {
		ResultNtro<I> result = new ResultNtro<>();

		applyReducer(result, (__,item) -> {
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
			public <R> void applyReducer(ResultNtro<R> result, Reducer<I, R> _reducer) {
				StreamNtro.this.applyReducer(result, (__,item) -> {
					try {

						if(matcher.matches(item)) {
							_reducer.reduce(result, item);
						}

					}catch(Throwable t) {
						result.registerException(t);
					}
				});
			}
		};
	}

	@Override
	public <R> Stream<R> map(Mapper<I,R> mapper) {
		return reduceToStream((result, reducer, item) -> {
			reducer.reduce(result, mapper.map(item));
		});
	}

	@Override
	public <R> Result<R> reduceToResult(R initialValue, ResultReducer<I, R> reducer) {
		ResultNtro<R> result = new ResultNtro<>(initialValue);

		applyReducer(result, (__, item) -> {
			try {

				result.registerValue(reducer.reduce(result.value(), item));

			}catch(Throwable t) {
				result.registerException(t);
			}
		});

		return result;
	}

	@Override
	public <R> Stream<R> reduceToStream(StreamReducer<I,R> reducer) {
		return new ReduceToStreamNtro<I,R>(this, reducer);
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
