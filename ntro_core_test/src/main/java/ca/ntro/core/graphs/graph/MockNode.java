package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilder;
import ca.ntro.core.graphs.generics.directed_graph.NodeId;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public class MockNode extends GraphNodeBuilderNtro<MockNode, MockEdge, MockNode> {

	public MockNode(NodeId nodeId,
			GenericDirectedGraphBuilder<MockNode, MockEdge, GraphSearchOptionsBuilder, MockNode, GenericDirectedGraph<MockNode, MockEdge, GraphSearchOptionsBuilder>> graphBuilder) {
		super(nodeId, graphBuilder);
	}
}
