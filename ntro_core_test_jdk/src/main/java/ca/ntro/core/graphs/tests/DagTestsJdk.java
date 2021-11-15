package ca.ntro.core.graphs.tests;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graphs.dag.Dag;
import ca.ntro.core.graphs.dag.DagNtro;
import ca.ntro.core.graphs.writers.DagWriterNtro;
import ca.ntro.core.initialization.InitializerTestJdk;
import ca.ntro.core.initialization.Ntro;

public class DagTestsJdk extends DagTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}
	
	@Test
	public void writeGraph01() {

		NodeMock nodeA = new NodeMock("A");
		NodeMock nodeB = new NodeMock("B");
		NodeMock nodeC = new NodeMock("C");
		
		EdgeMock edgeAB = new EdgeMock("AB");
		EdgeMock edgeBC = new EdgeMock("BC");
		
		Dag<NodeMock, EdgeMock> dag = new DagNtro<>();
		
		dag.addEdge(nodeA, edgeAB, nodeB);
		dag.addEdge(nodeB, edgeBC, nodeC);
		
		DagWriterNtro<NodeMock, EdgeMock> dagWriter = new DagWriterMock();
		dagWriter.write(dag);
		
		Ntro.asserter().assertFalse("Should not throw", exceptionThrower().hasThrown());
	}
	


}
