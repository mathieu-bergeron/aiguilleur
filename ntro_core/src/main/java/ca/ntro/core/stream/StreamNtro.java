package ca.ntro.core.stream;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.PairNtro;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class StreamNtro<I extends Object> 

       implements     Stream<I> {

	@Override
	public abstract void forEach_(Visitor<I> visitor) throws Throwable;
	
	@Override
	public <R> void applyReducer(ResultNtro<R> result, Reducer<I,R> reducer) {
		try {

			forEach_(i -> {

				reducer.reduce(result, i);

			});

		}catch(Throwable t) {

			result.registerException(t);

		}
	}

	@Override
	public boolean isEmpty() {
		return !ifSome(v -> true);
	}

	@Override
	public int size() {
		return reduceToResult(0, (accumulator, item) ->{

			return accumulator + 1;

		}).value();
	}
	
	@Override
	public I get(int index) {
		PairNtro<Integer, I> result = new PairNtro<>(0, null);
		
		result = reduceToResult(result, (accumulator, item) -> {
			if(accumulator.right() != null) {
				throw new Break();
			}
			
			if(accumulator.left() == index) {
				accumulator.setRight(item);
			}
			
			accumulator.setLeft(accumulator.left()+1);
			
			return accumulator;

		}).value();
		
		if(result.right() == null) {
			Ntro.exceptions().throwException(new IndexOutOfStreamException("Index out stream: " + index));
		}
		
		return result.right();
	}

	@Override
	public boolean ifAll(Matcher<I> matcher) {
		return ifNone(item -> !matcher.matches(item));
	}

	@Override
	public boolean ifSome(Matcher<I> matcher) {
		return reduceToResult(false, (accumulator, item) -> {
			if(accumulator == true) {
				throw new Break();
			}
			
			if(matcher.matches(item)) {
				accumulator = true;
			}
			
			return accumulator;
			
		}).value();
	}

	@Override
	public boolean ifNone(Matcher<I> matcher) {
		return reduceToResult(true, (accumulator, item) -> {
			if(accumulator == false) {
				throw new Break();
			}
			
			if(matcher.matches(item)) {
				accumulator = false;
			}
			
			return accumulator;
			
		}).value();
	}

	@Override
	public void forEach(Visitor<I> visitor) {
		try {

			forEach_(visitor);

		}catch(Throwable t) {

			Ntro.exceptions().throwException(t);

		}
	}

	@Override
	public I findFirst(Matcher<I> matcher) {
		return reduceToResult((I) null, (accumulator, item) -> {
			if(accumulator != null) {
				throw new Break();
			}
			
			if(matcher.matches(item)) {
				accumulator = item;
			}
			
			return accumulator;
			
			
		}).value();
	}

	@Override
	public Stream<I> findAll(Matcher<I> matcher) {
		return reduceToStream((item, visitor) -> {
			if(matcher.matches(item)) {
				visitor.visit(item);
			}
		});
	}
	
	@Override
	public Stream<I> append(Stream<I> other){
		return new StreamNtro<I>() {

			@Override
			public void forEach_(Visitor<I> visitor) throws Throwable {
				StreamNtro.this.forEach_(visitor);

				other.forEach_(visitor);
			}
		};
	}

	@Override
	public <R> Stream<R> map(Mapper<I,R> mapper) {
		return reduceToStream((item, visitor) -> {
			visitor.visit(mapper.map(item));
		});
	}

	@Override
	public <R> Result<R> reduceToResult(R initialValue, ResultReducer<I, R> reducer) {
		ResultNtro<R> result = new ResultNtro<>(initialValue);

		applyReducer(result, (__, item) -> {

			result.registerValue(reducer.reduce(result.value(), item));

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
