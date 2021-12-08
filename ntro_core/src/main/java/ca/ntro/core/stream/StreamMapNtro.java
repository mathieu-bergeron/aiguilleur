package ca.ntro.core.stream;

import ca.ntro.core.wrappers.result.ResultNtro;

public class StreamMapNtro<I extends Object, R extends Object> extends StreamNtro<R> {
	
	private StreamNtro<I> parentStream;
	private Mapper<I,R> mapper;

	public StreamMapNtro(StreamNtro<I> parentStream, Mapper<I, R> mapper) {
		this.parentStream = parentStream;
		this.mapper = mapper;
	}

	@Override
	public <RR> void reduceWithResult(ResultNtro<RR> result, Reducer<R, RR> _reducer) {
		parentStream.reduceWithResult(result, (__,item) -> {
			try {

				_reducer._reduce(result, mapper.map(item));

			}catch(Throwable t) {
				result.registerException(t);
			}
		});
	}
}
