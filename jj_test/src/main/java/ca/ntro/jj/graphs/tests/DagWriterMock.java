package ca.ntro.jj.graphs.tests;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.jj.graphs.dag.DagWriter;

public class DagWriterMock implements DagWriter<NodeMock, EdgeMock> {
	
	private Set<NodeMock> nodes = new HashSet<>();

	@Override
	public void writeNode(NodeMock node) {
		nodes.add(node);
	}

	@Override
	public void writeEdge(EdgeMock edge) {
		
	}

	public boolean containsNode(NodeMock node) {
		return nodes.contains(node);
	}

}
