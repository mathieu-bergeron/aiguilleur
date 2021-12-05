package ca.ntro.core.graphs.graph;


import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;

public class MockEdge extends EdgeNtro<MockNode, MockEdge, GraphSearchOptionsBuilder> {

	public MockEdge(MockNode from, EdgeType edgeType, MockNode to) {
		super(from, edgeType, to);
	}

}
