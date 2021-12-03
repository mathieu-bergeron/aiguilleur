package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Direction;

import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.EdgeNameNtro;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;

public class MockEdge extends EdgeNtro<MockNode, MockEdge, GraphSearchOptionsBuilder> {

	public MockEdge(MockNode from, EdgeType edgeName, MockNode to) {
		super(from, edgeName, to);
	}

	public MockEdge(MockNode from, String edgeName, MockNode to) {
		super(from, new EdgeNameNtro(Direction.FORWARD, edgeName), to);
	}

}
