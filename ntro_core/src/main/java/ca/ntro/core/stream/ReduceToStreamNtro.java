package ca.ntro.core.stream;

import ca.ntro.core.wrappers.result.ResultNtro;

public class ReduceToStreamNtro<R,I> extends StreamNtro<R> {
	
	private Stream<I> stream;
	private StreamReducer<R,I> streamReducer;
	
	public ReduceToStreamNtro(Stream<I> stream, StreamReducer<R,I> streamReducer) {
		this.stream = stream;
		this.streamReducer = streamReducer;
	}

	@Override
	public <RR> void reduceWithResult(ResultNtro<RR> result, Reducer<R,RR> _reducer) {
		stream.reduceWithResult(result, (__, item) -> {
			
			streamReducer.reduce(result, _reducer, item);
			
		});

	}
}
