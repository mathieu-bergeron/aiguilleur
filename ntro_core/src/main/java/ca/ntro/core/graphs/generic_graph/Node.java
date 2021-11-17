package ca.ntro.core.graphs.generic_graph;

public interface Node<NV extends NodeValue> {

	NodeId id();
	NV value();

}
