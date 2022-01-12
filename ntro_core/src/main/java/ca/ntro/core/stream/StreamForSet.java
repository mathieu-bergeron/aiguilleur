package ca.ntro.core.stream;

import java.util.Set;

public class StreamForSet<V extends Object> extends StreamNtro<V> {
	
	private Set<V> set;
	
	public StreamForSet(Set<V> set) {
		this.set = set;
	}
	
	
	@Override
	public void forEach_(Visitor<V> visitor) throws Throwable {
		for(V value : set) {
			visitor.visit(value);
		}
	}

}
