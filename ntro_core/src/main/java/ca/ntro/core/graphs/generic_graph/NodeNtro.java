package ca.ntro.core.graphs.generic_graph;

public class NodeNtro<NV extends NodeValue> implements Node<NV> {
	
	private NodeId id;
	private NV value;

	public NodeNtro(NodeId id, NV value) {
		this.id = id;
		this.value = value;
	}

	@Override
	public NodeId id() {
		return id;
	}

	@Override
	public NV value() {
		return value;
	}

}
