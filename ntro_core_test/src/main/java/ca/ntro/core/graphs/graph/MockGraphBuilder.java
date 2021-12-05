package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generic_graph.GenericGraphStructure;

public class MockGraphBuilder extends GraphBuilderNtro<MockNode, MockEdge, GraphSearchOptionsBuilder, MockNode>{

	public MockGraphBuilder(String graphName) {
		super(graphName);
	}

	@Override
	protected MockEdge createEdge(MockNode fromNode, EdgeType edgeType, MockNode toNode) {

		return new MockEdge(fromNode.toNode(), edgeType, toNode.toNode());
	}

	@Override
	protected Graph<MockNode, MockEdge, GraphSearchOptionsBuilder> createGraph(GraphId id,
			GenericGraphStructure<MockNode, MockEdge, GraphSearchOptionsBuilder> graphStructure) {
		
		return (Graph<MockNode, MockEdge, GraphSearchOptionsBuilder>) new MockGraph(id, graphStructure);
	}

	@Override
	protected MockNode createNodeBuilder(NodeId nodeId,
			GenericGraphBuilder<MockNode, MockEdge, GraphSearchOptionsBuilder, MockNode, GenericGraph<MockNode, MockEdge, GraphSearchOptionsBuilder>> graphBuilder) {

		return new MockNode(nodeId, graphBuilder);
	}
}
