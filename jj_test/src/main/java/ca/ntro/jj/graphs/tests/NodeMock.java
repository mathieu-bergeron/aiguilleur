package ca.ntro.jj.graphs.tests;

import ca.ntro.jj.graphs.dag.Node;
import ca.ntro.jj.graphs.dag.NodeId;

public class NodeMock implements Node {
	
	private String id;

	public NodeMock(String id) {
		this.id = id;
	}

	@Override
	public NodeId id() {
		return null;
	}

}
