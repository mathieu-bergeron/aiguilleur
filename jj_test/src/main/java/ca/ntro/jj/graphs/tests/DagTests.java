package ca.ntro.jj.graphs.tests;

import org.junit.Test;
import org.junit.Assert;

import ca.ntro.jj.graphs.dag.Dag;
import ca.ntro.jj.graphs.dag.DagJj;

public class DagTests {
	
	@Test
	public void addNode() {
		
		String nodeId = "id";
		
		NodeMock node = new NodeMock(nodeId);
		
		DagWriterMock dagWriter = new DagWriterMock();
		
		Dag dag = new DagJj();
		
		dag.addNode(node);
		
		dag.write(dagWriter);
		
		//Assert.assertTrue(dagWriter.containsNode(node));
	}

}
