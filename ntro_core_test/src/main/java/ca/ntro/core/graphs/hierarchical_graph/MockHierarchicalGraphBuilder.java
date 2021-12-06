package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.directed_graph.EdgeType;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilder;
import ca.ntro.core.graphs.generics.directed_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.directed_graph.GraphId;
import ca.ntro.core.graphs.generics.directed_graph.NodeId;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.HierarchicalGraphBuilderNtro;
import ca.ntro.core.graphs.generics.hierarchical_directed_graph.HierarchicalGraphSearchOptionsBuilder;

public class MockHierarchicalGraphBuilder extends HierarchicalGraphBuilderNtro<MockHierarchicalNode, 
                                                                               MockHierarchicalEdge, 
                                                                               HierarchicalGraphSearchOptionsBuilder,
                                                                               MockHierarchicalNodeBuilder,
                                                                               MockHierarchicalGraph> {

	public MockHierarchicalGraphBuilder(String graphName) {
		super(graphName);
	}

	@Override
	protected MockHierarchicalGraph createGraph(GraphId id,
			GenericGraphStructure<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder> graphStructure) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected MockHierarchicalNodeBuilder createNodeBuilder(NodeId nodeId,
			GenericDirectedGraphBuilder<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder, MockHierarchicalNodeBuilder, GenericDirectedGraph<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder>> graphBuilder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected MockHierarchicalEdge createEdge(MockHierarchicalNodeBuilder fromNode, EdgeType edgeType,
			MockHierarchicalNodeBuilder toNode) {
		// TODO Auto-generated method stub
		return null;
	}

}
