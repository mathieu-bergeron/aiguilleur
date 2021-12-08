package ca.ntro.core.stream;

import ca.ntro.core.wrappers.result.ResultNtro;

public class MapStreamNtro<I extends Object, R extends Object> extends StreamNtro<R> {
	
	private StreamNtro<I> inputStream;
	private Mapper<I,R> mapper;

	public MapStreamNtro(StreamNtro<I> inputStream, Mapper<I, R> mapper) {
		this.inputStream = inputStream;
		this.mapper = mapper;
	}

	@Override
	public <RR> void reduceWithResult(ResultNtro<RR> result, Reducer<R, RR> _reducer) {
		inputStream.reduceWithResult(result, (__,item) -> {
			try {

				_reducer._reduce(result, mapper.map(item));

			}catch(Throwable t) {
				result.registerException(t);
			}
		});
	}
}
