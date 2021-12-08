package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.common.EdgeType;

public class MockEdge extends EdgeNtro<MockNode, MockEdge> {

	public MockEdge() {
		super();
	}

	public MockEdge(MockNode from, EdgeType edgeType, MockNode to) {
		super(from, edgeType, to);
	}



}
