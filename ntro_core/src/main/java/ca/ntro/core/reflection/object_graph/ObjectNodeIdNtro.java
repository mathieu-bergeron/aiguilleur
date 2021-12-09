package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.common.NodeIdNtro;

public class ObjectNodeIdNtro 

       extends NodeIdNtro

       implements ObjectNodeId {
	
	private String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public ObjectNodeIdNtro(String key, String label) {
		super(key);
		setLabel(label);
	}

	@Override
	public String label() {
		return getLabel();
	}

}
