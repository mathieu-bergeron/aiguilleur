package ca.ntro.core.stream;

import ca.ntro.core.wrappers.result.ResultNtro;

public class ArrayStream<V extends Object> extends StreamNtro<V> {
	
	private V[] values;
	
	public ArrayStream(V[] values) {
		this.values = values;
	}

	@Override
	public <R> void applyReducer(ResultNtro<R> result, Reducer<V, R> reducer) {
		if(result.hasException()) return;

		for(V v : values) {
			if(result.hasException()) return;

			try {

				reducer.reduce(result, v);

			} catch (Throwable e) {

				result.registerException(e);
			}
		}
	}

}
