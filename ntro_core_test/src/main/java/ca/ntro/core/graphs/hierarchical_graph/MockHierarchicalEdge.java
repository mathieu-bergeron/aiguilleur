package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.EdgeNtro;
import ca.ntro.core.graphs.generics.generic_graph.EdgeType;
import ca.ntro.core.graphs.generics.generic_hierarchical_graph.HierarchicalGraphSearchOptionsBuilder;

public class MockHierarchicalEdge extends EdgeNtro<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder> {

	public MockHierarchicalEdge(MockHierarchicalNode from, EdgeType edgeType, MockHierarchicalNode to) {
		super(from, edgeType, to);
	}


}
