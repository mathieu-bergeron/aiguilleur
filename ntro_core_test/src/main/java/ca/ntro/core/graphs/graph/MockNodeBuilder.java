package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilder;
import ca.ntro.core.graphs.generics.directed_graph.NodeId;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public class MockNodeBuilder extends GraphNodeBuilderNtro<MockNode, MockEdge, MockNodeBuilder> {

	public MockNodeBuilder(NodeId nodeId,
			GenericDirectedGraphBuilder<MockNode, MockEdge, GraphSearchOptionsBuilder, MockNodeBuilder, GenericDirectedGraph<MockNode, MockEdge, GraphSearchOptionsBuilder>> graphBuilder) {
		super(nodeId, graphBuilder);
	}

}
