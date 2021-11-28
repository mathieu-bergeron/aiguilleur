package ca.ntro.core.graphs.tests;

import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.identifyers.Key;

public class MockEdgeValue implements EdgeValue {
	
	private String id;
	
	public MockEdgeValue(String id) {
		this.id = id;
	}


	@Override
	public String label() {
		return id;
	}


	@Override
	public Key name() {
		return new Key(id);
	}

}
