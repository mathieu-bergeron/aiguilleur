package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.graphs.generics.directed_graph.GenericNodeStructure;
import ca.ntro.core.graphs.generics.directed_graph.NodeId;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.HierarchicalGraphSearchOptionsBuilder;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.GenericHierarchicalNodeNtro;

public class MockHierarchicalNode extends GenericHierarchicalNodeNtro<MockHierarchicalNode, 
                                                                    MockHierarchicalEdge,
                                                                    HierarchicalGraphSearchOptionsBuilder>{

	public MockHierarchicalNode(NodeId id) {
		super(id);
	}

	@Override
	public GenericDirectedGraph<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder> parentGraph() {
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
