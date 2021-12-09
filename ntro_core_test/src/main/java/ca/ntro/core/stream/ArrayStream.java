package ca.ntro.core.stream;

import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.result.ResultNtro;

public class ArrayStream<V extends Object> extends StreamNtro<V> {
	
	private V[] values;
	
	public ArrayStream(V[] values) {
		this.values = values;
	}

	@Override
	public <R> void applyReducer(ResultNtro<R> result, Reducer<V, R> _reducer) {
		for(V v : values) {
			try {

				_reducer._reduce(result, v);

			} catch (Throwable e) {

				Ntro.exceptionThrower().throwException(e);
			}
		}
	}

}
