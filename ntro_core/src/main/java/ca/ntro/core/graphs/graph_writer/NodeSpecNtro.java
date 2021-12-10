package ca.ntro.core.graphs.graph_writer;

import ca.ntro.core.graphs.generics.graph.GenericNode;

public class NodeSpecNtro implements NodeSpec {
	
	private GenericNode<?,?,?> node;
	private String color;
	private String shape;

	public GenericNode<?,?,?> getNode() {
		return node;
	}

	public void setNode(GenericNode<?,?,?> node) {
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

	public NodeSpecNtro(GenericNode<?,?,?> node) {
		setNode(node);
	}

	@Override
	public String id() {
		return node.id().toKey().toString();
	}

	@Override
	public String label() {
		return node.label();
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
