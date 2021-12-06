package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.directed_graph.EdgeNtro;
import ca.ntro.core.graphs.generics.directed_graph.EdgeType;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.HierarchicalGraphSearchOptionsBuilder;

public class MockHierarchicalEdge extends EdgeNtro<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder> {

	public MockHierarchicalEdge(MockHierarchicalNode from, EdgeType edgeType, MockHierarchicalNode to) {
		super(from, edgeType, to);
	}


}
