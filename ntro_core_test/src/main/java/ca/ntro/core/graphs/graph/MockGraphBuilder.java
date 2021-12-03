package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;

public class MockGraphBuilder extends GraphBuilderNtro<MockNode, MockEdge, GraphSearchOptionsBuilder>{


	public MockGraphBuilder(String graphName) {
		super(graphName);
	}

	@Override
	protected MockEdge createEdge(MockNode fromNode, EdgeType edgeType, MockNode toNode) {

		return new MockEdge(fromNode, edgeType, toNode);
	}

	@Override
	protected MockNode createNode(NodeId nodeId,
			GenericGraphBuilder<MockNode, MockEdge, GraphSearchOptionsBuilder, GenericGraph<MockNode, MockEdge, GraphSearchOptionsBuilder>> graphBuilder) {
		
		return new MockNode(nodeId, graphBuilder);
	}




}
