package ca.ntro.core.graphs;

import ca.ntro.core.path.PathName;

public class EdgeValueNull implements EdgeValue {

	@Override
	public PathName name() {
		return new PathName("");
	}

	@Override
	public String label() {
		return "";
	}
}
