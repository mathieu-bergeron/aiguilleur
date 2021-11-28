package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;

public class NodeSpecNtro implements NodeSpec {
	
	private Node<? extends NodeValue> node;

	public Node<? extends NodeValue> getNode() {
		return node;
	}

	public void setNode(Node<? extends NodeValue> node) {
		this.node = node;
	}
	
	public NodeSpecNtro(Node<? extends NodeValue> node) {
		setNode(node);
	}

	@Override
	public String id() {
		return node.id().toKey().toString();
	}

	@Override
	public String label() {
		return node.value().label();
	}
}
