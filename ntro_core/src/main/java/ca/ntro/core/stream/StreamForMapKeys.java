package ca.ntro.core.stream;

import java.util.Map;

public class StreamForMapKeys<K extends Object> extends StreamNtro<K> {
	
	private Map<K, ?> map;
	
	public StreamForMapKeys(Map<K, ?> map) {
		this.map = map;
	}
	
	
	@Override
	public void forEach_(Visitor<K> visitor) throws Throwable {
		for(K value : map.keySet()) {
			visitor.visit(value);
		}
	}

}
