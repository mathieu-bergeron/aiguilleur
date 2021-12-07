package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.NodeId;
import ca.ntro.core.graphs.generics.generic_hierarchical_graph.GenericHierarchicalNodeNtro;
import ca.ntro.core.graphs.generics.generic_hierarchical_graph.HierarchicalSearchOptionsBuilder;

public class MockHierarchicalNode extends HierarchicalGraphNodeNtro<MockHierarchicalNode, 
                                                                    MockHierarchicalEdge>{

	public MockHierarchicalNode() {
		super();
	}

	public MockHierarchicalNode(NodeId id) {
		super(id);
	}
}
