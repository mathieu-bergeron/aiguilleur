package ca.ntro.core.graphs.tests;

import org.junit.Test;

import ca.ntro.core.graphs.dag.Dag;
import ca.ntro.core.graphs.dag.DagJj;
import ca.ntro.core.initialization.Jj;

public class DagTests {
	
	@Test
	public void addNode() {
		
		String nodeId = "id";
		
		NodeMock node = new NodeMock(nodeId);
		
		DagWriterMock dagWriter = new DagWriterMock();
		
		Dag dag = new DagJj();
		
		dag.addNode(node);
		
		dag.write(dagWriter);
		
		Jj.asserter().assertTrue("Should contain node", dagWriter.containsNode(node));
	}

}
