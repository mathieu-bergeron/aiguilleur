package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generics.generic_graph.GenericNodeStructure;
import ca.ntro.core.graphs.generics.generic_graph.NodeId;
import ca.ntro.core.graphs.generics.generic_hierarchical_graph.GenericHierarchicalNodeNtro;
import ca.ntro.core.graphs.generics.generic_hierarchical_graph.HierarchicalGraphSearchOptionsBuilder;

public class MockHierarchicalNode extends GenericHierarchicalNodeNtro<MockHierarchicalNode, 
                                                                    MockHierarchicalEdge,
                                                                    HierarchicalGraphSearchOptionsBuilder>{

	public MockHierarchicalNode(NodeId id) {
		super(id);
	}

	@Override
	public GenericGraph<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder> parentGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isStartNode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected GenericNodeStructure<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder> nodeStructure() {
		// TODO Auto-generated method stub
		return null;
	}

}