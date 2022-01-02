package ca.ntro.core.stream;


// JSweet: as a separate class to avoid typing errors
public class ReduceToStreamNtro<I,R> extends StreamNtro<R> {
	
	private Stream<I> stream;
	private StreamReducer<I,R> streamReducer;
	
	public ReduceToStreamNtro(Stream<I> stream, StreamReducer<I,R> streamReducer) {
		this.stream = stream;
		this.streamReducer = streamReducer;
	}

	@Override
	public void forEach_(Visitor<R> visitor) throws Throwable {
		stream.forEach_(item -> {
			streamReducer.reduce(item, mappedItem -> {
				visitor.visit(mappedItem);
			});
		});
	}

}
