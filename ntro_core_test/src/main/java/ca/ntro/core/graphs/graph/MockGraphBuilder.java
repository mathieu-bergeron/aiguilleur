package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generic_graph.GenericGraphStructure;

public class MockGraphBuilder extends GraphBuilderNtro<MockNode, MockEdge, GraphSearchOptionsBuilder, MockNodeBuilder>{

	public MockGraphBuilder(String graphName) {
		super(graphName);
	}

	@Override
	protected MockEdge createEdge(MockNodeBuilder fromNode, EdgeType edgeType, MockNodeBuilder toNode) {

		return new MockEdge(fromNode.toNode(), edgeType, toNode.toNode());
	}

	@Override
	protected Graph<MockNode, MockEdge, GraphSearchOptionsBuilder> createGraph(GraphId id,
			GenericGraphStructure<MockNode, MockEdge, GraphSearchOptionsBuilder> graphStructure) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected MockNodeBuilder createNodeBuilder(NodeId nodeId,
			GenericGraphBuilder<MockNode, MockEdge, GraphSearchOptionsBuilder, MockNodeBuilder, GenericGraph<MockNode, MockEdge, GraphSearchOptionsBuilder>> graphBuilder) {
		// TODO Auto-generated method stub
		return null;
	}
}
