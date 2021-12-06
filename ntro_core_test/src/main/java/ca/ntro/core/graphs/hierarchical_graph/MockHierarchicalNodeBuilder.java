package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilder;
import ca.ntro.core.graphs.generics.directed_graph.NodeId;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.HierarchicalGraphSearchOptionsBuilder;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.HierarchicalNodeBuilderNtro;

public class MockHierarchicalNodeBuilder extends HierarchicalNodeBuilderNtro<MockHierarchicalNode, 
                                                                      MockHierarchicalEdge, 
                                                                      HierarchicalGraphSearchOptionsBuilder,
                                                                      MockHierarchicalNodeBuilder> {

	public MockHierarchicalNodeBuilder(NodeId nodeId,
			GenericDirectedGraphBuilder<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder, MockHierarchicalNodeBuilder, GenericDirectedGraph<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder>> graphBuilder) {
		super(nodeId, graphBuilder);
	}

}
