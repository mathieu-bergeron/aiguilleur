package ca.ntro.core.reflection;

import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.path.PathName;

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
	public PathName name() {
		return new PathName(className());
	}

	@Override
	public String label() {
		return className();
	}

}
