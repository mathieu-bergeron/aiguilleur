package ca.ntro.jj.graphs.tests;

import ca.ntro.jj.graphs.dag.DagWriter;

public class DagWriterMock implements DagWriter<NodeMock, EdgeMock> {

	@Override
	public void writeNode(NodeMock node) {
		
	}

	@Override
	public void writeEdge(EdgeMock edge) {
		
	}

	public boolean containsNode(NodeMock node) {
		return false;
	}

}
