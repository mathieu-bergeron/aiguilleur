package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.common.NodeId;

public class MockNode extends NodeNtro<MockNode, MockEdge> {
	
	public MockNode() {
		super();
	}

	public MockNode(NodeId nodeId) {
		super(nodeId);
	}

	public MockNode(String nodeId) {
		super(nodeId);
	}

}
