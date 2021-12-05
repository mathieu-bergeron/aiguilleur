package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generic_graph.GenericNodeBuilderNtro;
import ca.ntro.core.graphs.generic_graph.GenericNodeStructure;

public class MockNode extends GenericNodeBuilderNtro<MockNode, MockEdge, GraphSearchOptionsBuilder, MockNode> {

	public MockNode(NodeId nodeId,
			GenericGraphBuilder<MockNode, MockEdge, GraphSearchOptionsBuilder, MockNode, GenericGraph<MockNode, MockEdge, GraphSearchOptionsBuilder>> graphBuilder) {
		super(nodeId, graphBuilder);
	}

	@Override
	protected GenericNodeStructure<MockNode, MockEdge, GraphSearchOptionsBuilder> nodeStructure() {
		// TODO Auto-generated method stub
		return null;
	}


}
