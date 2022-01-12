package ca.ntro.core.stream;

import java.util.Map;

public class StreamForMapValues<V extends Object> extends StreamNtro<V> {
	
	private Map<?, V> map;
	
	public StreamForMapValues(Map<?, V> map) {
		this.map = map;
	}
	
	
	@Override
	public void forEach_(Visitor<V> visitor) throws Throwable {
		for(V value : map.values()) {
			visitor.visit(value);
		}
	}

}
