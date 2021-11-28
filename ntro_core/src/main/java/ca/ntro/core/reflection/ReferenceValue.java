package ca.ntro.core.reflection;

import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.identifyers.Key;

public class ReferenceValue implements EdgeValue {
	
	private String attributeName;
	
	public ReferenceValue(String attributeName) {
		this.attributeName = attributeName;
	}
	
	public String attributeName() {
		return attributeName;
	}

	@Override
	public Key name() {
		return new Key(attributeName());
	}

	@Override
	public String label() {
		return attributeName();
	}

}
