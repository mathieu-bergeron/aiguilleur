package ca.ntro.core.graphs.dag;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graphs.dag.Dag;
import ca.ntro.core.graphs.dag.DagNtro;
import ca.ntro.core.graphs.dag.DagTests;
import ca.ntro.core.initialization.InitializerTestJdk;
import ca.ntro.core.initialization.Ntro;

public class DagTestsJdk extends DagTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}
	
	@Test
	public void writeGraph01() {

		/*
		MockNodeValue nodeA = new MockNodeValue("A");
		MockNodeValue nodeB = new MockNodeValue("B");
		MockNodeValue nodeC = new MockNodeValue("C");
		
		MockEdgeValue edgeAB = new MockEdgeValue("AB");
		MockEdgeValue edgeBC = new MockEdgeValue("BC");
		
		Dag<MockNodeValue, MockEdgeValue> dag = new DagNtro<>();
		
		dag.addEdge(nodeA, edgeAB, nodeB);
		dag.addEdge(nodeB, edgeBC, nodeC);
		
		DagWriterNtro<MockNodeValue, MockEdgeValue> dagWriter = new DagWriterMock();
		dagWriter.write(dag);
		
		Ntro.asserter().assertFalse("Should not throw", exceptionThrower().hasThrown());
		*/
	}
	


}
