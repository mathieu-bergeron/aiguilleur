package ca.ntro.core.graphs.tests;

import ca.ntro.core.graphs.graph.Node;
import ca.ntro.core.graphs.graph.NodeId;

public class NodeMock implements Node {
	
	private NodeId id;

	public NodeMock(String id) {
		this.id = new NodeId(id);
	}

	@Override
	public NodeId id() {
		return id;
	}

	@Override
	public String label() {
		return id.toString();
	}

}
