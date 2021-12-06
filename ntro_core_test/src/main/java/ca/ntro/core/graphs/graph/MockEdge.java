package ca.ntro.core.graphs.graph;


import ca.ntro.core.graphs.generics.directed_graph.EdgeNtro;
import ca.ntro.core.graphs.generics.directed_graph.EdgeType;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public class MockEdge extends GraphEdgeNtro<MockNode, MockEdge> {

	public MockEdge(MockNode from, EdgeType edgeType, MockNode to) {
		super(from, edgeType, to);
	}

}
