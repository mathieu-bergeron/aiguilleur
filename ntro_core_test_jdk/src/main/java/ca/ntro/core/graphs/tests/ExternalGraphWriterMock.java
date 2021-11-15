package ca.ntro.core.graphs.tests;

import ca.ntro.core.graphs.writers.ExternalGraphWriter;
import ca.ntro.core.path.Filepath;

public class ExternalGraphWriterMock implements ExternalGraphWriter<NodeMock, EdgeMock> {
	
	private Filepath basepath;

	public ExternalGraphWriterMock(Filepath basepath) {
		this.basepath = basepath;
	}

	@Override
	public void writeEdge(NodeMock from, EdgeMock edge, NodeMock to) {
		
	}

	@Override
	public void writeNode(NodeMock node) {
		
	}
}
