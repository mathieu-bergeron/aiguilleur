package ca.ntro.core.graphs.tests;

import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.path.PathName;

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
	public PathName name() {
		return new PathName(id);
	}

}
