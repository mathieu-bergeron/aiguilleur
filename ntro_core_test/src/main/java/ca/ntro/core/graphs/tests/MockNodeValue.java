package ca.ntro.core.graphs.tests;

import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.identifyers.Key;

public class MockNodeValue implements NodeValue {
	
	private String id;

	public MockNodeValue(String id) {
		this.id = id;
	}


	@Override
	public String label() {
		return id.toString();
	}


	@Override
	public Key name() {
		return new Key(id);
	}

}
