package ca.ntro.core.stream;

import ca.ntro.core.wrappers.result.ResultNtro;

public class ReduceToStreamNtro<I,M,RR> extends StreamNtro<RR> {
	
	private Stream<I> stream;
	private StreamReducer<I,M,RR> streamReducer;
	
	public ReduceToStreamNtro(Stream<I> stream, StreamReducer<I,M,RR> streamReducer) {
		this.stream = stream;
		this.streamReducer = streamReducer;
	}

	@Override
	public <R> void reduceWithResult(ResultNtro<R> result, Reducer<RR, R> _reducer) {
		stream.reduceWithResult(result, (__, item) -> {
			streamReducer.reduce(result, _reducer, item);
		});
		
	}
}
