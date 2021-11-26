package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeMatcher;
import ca.ntro.core.graphs.NodeValue;

public class NodeMatcherByValue<NV extends NodeValue, N extends Node<NV>> implements NodeMatcher<NV,N> {

	private NV value;

	public NodeMatcherByValue(NV value) {
		this.value = value;
	}

	@Override
	public boolean matches(N node) {
		return this.value.equals(node.value());
	}

}
