package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.generic_graph.EdgeType;

public class MockEdge extends GraphEdgeNtro<MockNode, MockEdge> {

	public MockEdge() {
		super();
	}

	public MockEdge(MockNode from, EdgeType edgeType, MockNode to) {
		super(from, edgeType, to);
	}



}
