package ca.ntro.core.graphs.graph;


import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generic_graph.NodeBuilderNtro;

public class MockNode extends NodeBuilderNtro<MockNode, MockEdge, GraphSearchOptionsBuilder> {

	public MockNode(NodeId nodeId, 
			        GenericGraphBuilderNtro<MockNode, MockEdge, GraphSearchOptionsBuilder, GenericGraph<MockNode, MockEdge, GraphSearchOptionsBuilder>> graph) {

		super(nodeId, graph);
	}

	@Override
	protected MockEdge createEdge(MockNode fromNode, EdgeType edgeType, MockNode toNode) {
		return new MockEdge(fromNode, edgeType, toNode);
	}

}
