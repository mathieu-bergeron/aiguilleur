package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.NodeId;

public class MockGraphBuilder extends GraphBuilderNtro<MockNode, MockEdge, GraphSearchOptionsBuilder>{

	@Override
	public MockEdge addEdge(MockNode fromNode, String edgeName, MockNode toNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MockEdge addEdge(MockNode fromNode, EdgeType edgeType, MockNode toNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected MockNode createNode(NodeId nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	@Override
	protected MockEdge createEdge(MockNode fromNode, EdgeType edgeType, MockNode toNode) {
		return new MockEdge(fromNode, edgeType, toNode);
	}*/


}
