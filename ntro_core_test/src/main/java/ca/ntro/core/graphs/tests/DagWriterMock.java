package ca.ntro.core.graphs.tests;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.graphs.dag.DagWriter;

public class DagWriterMock implements DagWriter<NodeMock, EdgeMock> {
	
	private Set<NodeMock> nodes = new HashSet<>();

	@Override
	public void writeNode(NodeMock node) {
		nodes.add(node);
	}

	public boolean containsNode(NodeMock node) {
		return nodes.contains(node);
	}

	@Override
	public void writeEdge(NodeMock from, EdgeMock edge, NodeMock to) {
		// TODO Auto-generated method stub
		
	}

}
