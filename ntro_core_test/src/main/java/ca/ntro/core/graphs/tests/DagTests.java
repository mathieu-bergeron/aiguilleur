package ca.ntro.core.graphs.tests;

import org.junit.Test;

import ca.ntro.core.graphs.dag.Dag;
import ca.ntro.core.graphs.dag.DagNtro;
import ca.ntro.core.initialization.Ntro;

public class DagTests {
	
	@Test
	public void addNode() {
		
		String nodeId = "id";
		
		NodeMock node = new NodeMock(nodeId);
		
		DagWriterMock dagWriter = new DagWriterMock();
		
		Dag dag = new DagNtro();
		
		dag.addNode(node);
		
		dag.write(dagWriter);
		
		Ntro.asserter().assertTrue("Should contain node", dagWriter.containsNode(node));
	}

}
