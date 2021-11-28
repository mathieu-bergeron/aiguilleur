package ca.ntro.core.graphs;

import ca.ntro.core.identifyers.Key;

public class EdgeValueNull implements EdgeValue {

	@Override
	public Key name() {
		return new Key("");
	}

	@Override
	public String label() {
		return "";
	}
}
