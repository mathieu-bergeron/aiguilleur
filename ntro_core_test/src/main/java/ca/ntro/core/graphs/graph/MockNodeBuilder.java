package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generic_graph.GenericNodeBuilderNtro;
import ca.ntro.core.graphs.generic_graph.GenericNodeStructure;

public class MockNodeBuilder extends GenericNodeBuilderNtro<MockNode, MockEdge, GraphSearchOptionsBuilder, MockNodeBuilder> {

	public MockNodeBuilder(NodeId nodeId,
			GenericGraphBuilder<MockNode, MockEdge, GraphSearchOptionsBuilder, MockNodeBuilder, GenericGraph<MockNode, MockEdge, GraphSearchOptionsBuilder>> graphBuilder) {
		super(nodeId, graphBuilder);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected GenericNodeStructure<MockNode, MockEdge, GraphSearchOptionsBuilder> nodeStructure() {
		// TODO Auto-generated method stub
		return null;
	}

}
