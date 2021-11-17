package ca.ntro.core.graphs.generic_graph;

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
