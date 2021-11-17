package ca.ntro.core.reflection;

import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.path.PathName;

public class ReferenceValue implements EdgeValue {
	
	private String attributeName;
	
	public ReferenceValue(String attributeName) {
		this.attributeName = attributeName;
	}
	
	public String attributeName() {
		return attributeName;
	}

	@Override
	public PathName name() {
		return new PathName(attributeName());
	}

	@Override
	public String label() {
		return attributeName();
	}

}
