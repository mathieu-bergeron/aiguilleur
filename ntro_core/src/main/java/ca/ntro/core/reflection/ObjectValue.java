package ca.ntro.core.reflection;

import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.identifyers.Key;

public class ObjectValue implements NodeValue {
	
	private Object object;
	
	public ObjectValue(Object object) {
		this.object = object;
	}
	
	public Object object() {
		return object;
	}
	
	private String className() {
		return object().getClass().getSimpleName();
	}

	@Override
	public Key name() {
		return new Key(className());
	}

	@Override
	public String label() {
		return className();
	}

}
