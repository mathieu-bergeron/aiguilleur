package ca.ntro.core.graphs.graph;


import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generic_graph.GenericNodeBuilderNtro;

public class MockNode extends GenericNodeBuilderNtro<MockNode, MockEdge, GraphSearchOptionsBuilder> {

	public MockNode(NodeId nodeId,
			        GenericGraphBuilder<MockNode, MockEdge, GraphSearchOptionsBuilder, GenericGraph<MockNode, MockEdge, GraphSearchOptionsBuilder>> graphBuilder) {

		super(nodeId, graphBuilder);
	}


}
