package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.NodeId;

public class MockNode extends GraphNodeNtro<MockNode, MockEdge> {

	public MockNode(NodeId nodeId) {
		super(nodeId);
	}

	public MockNode(String nodeId) {
		super(nodeId);
	}

}
