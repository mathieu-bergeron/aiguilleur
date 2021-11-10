package ca.ntro.jj.graphs.tests;

import ca.ntro.jj.graphs.dag.Node;
import ca.ntro.jj.graphs.dag.NodeId;

public class NodeMock implements Node {
	
	private NodeId id;

	public NodeMock(String id) {
		this.id = new NodeId(id);
	}

	@Override
	public NodeId id() {
		return id;
	}

}
