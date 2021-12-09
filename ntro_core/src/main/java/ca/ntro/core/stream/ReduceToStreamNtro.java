package ca.ntro.core.stream;

import ca.ntro.core.wrappers.result.ResultNtro;

public class ReduceToStreamNtro<I,R> extends StreamNtro<R> {
	
	private Stream<I> stream;
	private StreamReducer<I,R> streamReducer;
	
	public ReduceToStreamNtro(Stream<I> stream, StreamReducer<I,R> streamReducer) {
		this.stream = stream;
		this.streamReducer = streamReducer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <RR> void reduceWithResult(ResultNtro<RR> result, Reducer<R, RR> _reducer) {

		// JSweet: to avoid typing errors
		ResultNtro resultR = (ResultNtro) result;

		stream.reduceWithResult(resultR, (__, item) -> {
			streamReducer.reduce(resultR, (Reducer<R,R>) _reducer, item);
		});
	}
}
