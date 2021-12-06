package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.EdgeType;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilder;
import ca.ntro.core.graphs.generics.directed_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.directed_graph.GraphId;
import ca.ntro.core.graphs.generics.directed_graph.NodeId;
import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public class MockGraphBuilder extends GraphBuilderNtro<MockNode, MockEdge, MockNode> {

	public MockGraphBuilder(String graphName) {
		super(graphName);
	}

	@Override
	protected GenericGraph<MockNode, MockEdge, GraphSearchOptionsBuilder> createGraph(GraphId id,
			GenericGraphStructure<MockNode, MockEdge, GraphSearchOptionsBuilder> graphStructure) {
		
		return new MockGraph(id, graphStructure);
	}

	@Override
	protected MockNode createNodeBuilder(NodeId nodeId,
			GenericDirectedGraphBuilder<MockNode, MockEdge, GraphSearchOptionsBuilder, MockNode, GenericDirectedGraph<MockNode, MockEdge, GraphSearchOptionsBuilder>> graphBuilder) {
		
		return new MockNode(nodeId, graphBuilder);
	}

	@Override
	protected MockEdge createEdge(MockNode fromNode, EdgeType edgeType, MockNode toNode) {
		return new MockEdge(fromNode, edgeType, toNode);
	}
}
