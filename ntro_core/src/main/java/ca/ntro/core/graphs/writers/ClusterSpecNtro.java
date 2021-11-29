package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.Node;

public class ClusterSpecNtro implements ClusterSpec {

	private Node<?,?,?> node;

	public Node<?,?,?> getNode() {
		return node;
	}

	public void setNode(Node<?,?,?> node) {
		this.node = node;
	}
	
	public ClusterSpecNtro(Node<?,?,?> node) {
		setNode(node);
	}

	@Override
	public String id() {
		return node.id().toKey().toString();
	}

	@Override
	public String label() {
		return node.id().label();
	}
}
