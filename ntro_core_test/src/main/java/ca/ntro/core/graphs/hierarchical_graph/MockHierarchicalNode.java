package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.NodeId;

public class MockHierarchicalNode extends HierarchicalGraphNodeNtro<MockHierarchicalNode, 
                                                                    MockHierarchicalEdge>{

	public MockHierarchicalNode() {
		super();
	}

	public MockHierarchicalNode(NodeId id) {
		super(id);
	}
}
