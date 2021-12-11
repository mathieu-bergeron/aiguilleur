package ca.ntro.core.stream;

public class ArrayStream<V extends Object> extends StreamNtro<V> {
	
	private V[] values;
	
	public ArrayStream(V[] values) {
		this.values = values;
	}

	@Override
	public void _forEach(Visitor<V> visitor) throws Throwable {
		for(V value : values) {
			visitor.visit(value);
		}
	}

}
