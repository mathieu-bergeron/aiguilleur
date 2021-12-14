package ca.ntro.core.graphs.directed_graph;

public class MockEdge extends DirectedEdgeNtro<MockNode, MockEdge> {

	public MockEdge() {
		super();
	}

	public MockEdge(MockNode fromNode, String edgeName, MockNode toNode) {
		super(fromNode, edgeName, toNode);
	}

}
