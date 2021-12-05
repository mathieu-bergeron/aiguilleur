package ca.ntro.core.graphs.graph;


import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generic_graph.GenericNodeNtro;
import ca.ntro.core.graphs.generic_graph.GenericNodeStructure;

public class MockNode extends GenericNodeNtro<MockNode, MockEdge, GraphSearchOptionsBuilder> {

	public MockNode(NodeId nodeId) {
		super(nodeId);
	}

	@Override
	public GenericGraph<MockNode, MockEdge, GraphSearchOptionsBuilder> parentGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isStartNode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected GenericNodeStructure<MockNode, MockEdge, GraphSearchOptionsBuilder> nodeStructure() {
		// TODO Auto-generated method stub
		return null;
	}



}
