package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.EdgeType;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilder;
import ca.ntro.core.graphs.generics.directed_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.directed_graph.GraphId;
import ca.ntro.core.graphs.generics.directed_graph.NodeId;
import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public class MockGraphBuilder extends GraphBuilderNtro<MockNode, MockEdge, MockNodeBuilder> {

	public MockGraphBuilder(String graphName) {
		super(graphName);
	}

	public MockGraphBuilder(MockGraph mockGraph) {
		super();
	}

	@Override
	protected GenericGraph<MockNode, MockEdge, GraphSearchOptionsBuilder> createGraph(GraphId id,
			GenericGraphStructure<MockNode, MockEdge, GraphSearchOptionsBuilder> graphStructure) {

		return new MockGraph(id, graphStructure);
	}

	@Override
	protected MockNodeBuilder createNodeBuilder(NodeId nodeId,
			GenericDirectedGraphBuilder<MockNode, MockEdge, GraphSearchOptionsBuilder, MockNodeBuilder, GenericDirectedGraph<MockNode, MockEdge, GraphSearchOptionsBuilder>> graphBuilder) {
		
		return new MockNodeBuilder(nodeId, graphBuilder);
	}

	@Override
	protected MockEdge createEdge(MockNodeBuilder fromNode, EdgeType edgeType, MockNodeBuilder toNode) {
		
		return new MockEdge(fromNode.node(), edgeType, toNode.node());
	}
}
