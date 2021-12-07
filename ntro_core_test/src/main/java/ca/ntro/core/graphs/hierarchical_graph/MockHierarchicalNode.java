package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.NodeId;
import ca.ntro.core.graphs.generics.generic_hierarchical_graph.GenericHierarchicalNodeNtro;
import ca.ntro.core.graphs.generics.generic_hierarchical_graph.HierarchicalGraphSearchOptionsBuilder;

public class MockHierarchicalNode extends GenericHierarchicalNodeNtro<MockHierarchicalNode, 
                                                                      MockHierarchicalEdge,
                                                                      HierarchicalGraphSearchOptionsBuilder>{

	public MockHierarchicalNode() {
		super();
	}

	public MockHierarchicalNode(NodeId id) {
		super(id);
	}
}
