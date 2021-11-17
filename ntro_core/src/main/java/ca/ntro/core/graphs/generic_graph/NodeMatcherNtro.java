package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.NodeMatcher;
import ca.ntro.core.graphs.NodeValue;

public class NodeMatcherNtro<NV extends NodeValue> implements NodeMatcher<NV> {

	private NV value;

	public NodeMatcherNtro(NV value) {
		this.value = value;
	}

	@Override
	public boolean matches(NV value) {
		return this.value.equals(value);
	}

}
