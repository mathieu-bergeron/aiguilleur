package ca.ntro.jj.graphs.tests;

import org.junit.Test;

import ca.ntro.jj.graphs.dag.Dag;
import ca.ntro.jj.graphs.dag.DagJj;
import ca.ntro.jj.initialization.Jj;

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
