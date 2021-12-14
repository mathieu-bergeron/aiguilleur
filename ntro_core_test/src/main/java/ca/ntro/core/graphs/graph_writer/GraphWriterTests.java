package ca.ntro.core.graphs.graph_writer;



import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graph_writer.ClusterNotFoundException;
import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.graphs.generics.graph.GraphWriterOptionsNtro;
import ca.ntro.core.graphs.generics.graph.NodeNotFoundException;
import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;

public class GraphWriterTests {
	
	protected ExceptionThrowerMock registerMockExceptionThrower() {
		ExceptionThrowerMock exceptionThrowerMock = new ExceptionThrowerMock();

		InitializerTest.registerExceptionThrower(exceptionThrowerMock);

		return exceptionThrowerMock;
	}

	@BeforeClass
	public static void initialize() {
		InitializerTest.initialize();
	}
	
	
	@Test
	public void graphWriter01() throws NodeNotFoundException, ClusterNotFoundException {
		GraphWriter writer = Ntro.graphWriter();
		GraphId id = GraphId.fromGraphName("graphWriter01");
		GraphWriterOptionsNtro options = new GraphWriterOptionsNtro();
		
		MockNodeSpec nodeA = new MockNodeSpec();

		// TODO
		
		writer.initialize(id, options);
		
		//writer.writeDot();
		//writer.writePng();
	}

	
}
