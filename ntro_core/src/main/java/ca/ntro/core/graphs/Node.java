package ca.ntro.core.graphs;

public interface Node<NV extends NodeValue> {

	NodeId id();
	NV value();

}
