package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.Node;

public class NodeSpecNtro implements NodeSpec {
	
	private Node<?,?,?> node;
	private String color;
	private String shape;

	public Node<?,?,?> getNode() {
		return node;
	}

	public void setNode(Node<?,?,?> node) {
		this.node = node;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public NodeSpecNtro(Node<?,?,?> node) {
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

	@Override
	public String color() {
		return getColor();
	}

	@Override
	public String shape() {
		return getShape();
	}
}
