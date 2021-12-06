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
                                                                               MockHierarchicalNode,
                                                                               MockHierarchicalGraph> {

	public MockHierarchicalGraphBuilder(String graphName) {
		super(graphName);
	}

	@Override
	protected MockHierarchicalGraph createGraph(GraphId id,
			GenericGraphStructure<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder> graphStructure) {
		
		return new MockHierarchicalGraph(id, graphStructure);
	}

	@Override
	protected MockHierarchicalNode createNodeBuilder(NodeId nodeId,
			GenericDirectedGraphBuilder<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder, MockHierarchicalNode, GenericDirectedGraph<MockHierarchicalNode, MockHierarchicalEdge, HierarchicalGraphSearchOptionsBuilder>> graphBuilder) {
		
		return new MockHierarchicalNode(nodeId, graphBuilder);
	}

	@Override
	protected MockHierarchicalEdge createEdge(MockHierarchicalNode fromNode, EdgeType edgeType,
			MockHierarchicalNode toNode) {
		
		return new MockHierarchicalEdge(fromNode, edgeType, toNode);
	}

}
