package ca.ntro.core.graph_writer;

import ca.ntro.core.graphs.generics.graph.GenericNode;
import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalNode;

public class NodeSpecNtro implements NodeSpec {
	
	private GenericNode<?,?,?> node;
	private String color;
	private String shape;
	private String label;

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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public NodeSpecNtro() {
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
		String label = null;

		if(getLabel() == null) {
			
			label = node.label();
			
		}else {
			
			label = getLabel();
		}

		return label;
	}

	@Override
	public String color() {
		return getColor();
	}

	@Override
	public String shape() {
		return getShape();
	}

	@Override
	public boolean isCluster() {
		boolean isCluster = false;
		
		if(getNode() instanceof GenericHierarchicalNode<?,?,?>) {
			isCluster = ((GenericHierarchicalNode<?,?,?>) getNode()).hasSubNodes();
		}

		return isCluster;
	}
}
