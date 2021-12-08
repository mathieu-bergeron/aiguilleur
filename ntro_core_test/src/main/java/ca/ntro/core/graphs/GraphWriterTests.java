package ca.ntro.core.graphs;



import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graphs.generics.generic_graph.Direction;
import ca.ntro.core.graphs.generics.generic_graph.EdgeTypeNtro;
import ca.ntro.core.graphs.generics.generic_graph.GraphId;
import ca.ntro.core.graphs.generics.generic_graph.NodeNotFoundException;
import ca.ntro.core.graphs.graph.MockEdge;
import ca.ntro.core.graphs.graph.MockNode;
import ca.ntro.core.graphs.writers.ClusterAlreadyAddedException;
import ca.ntro.core.graphs.writers.ClusterNotFoundException;
import ca.ntro.core.graphs.writers.ClusterSpec;
import ca.ntro.core.graphs.writers.ClusterSpecNtro;
import ca.ntro.core.graphs.writers.EdgeAlreadyAddedException;
import ca.ntro.core.graphs.writers.EdgeSpecNtro;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.graphs.writers.GraphWriterException;
import ca.ntro.core.graphs.writers.GraphWriterOptionsNtro;
import ca.ntro.core.graphs.writers.NodeAlreadyAddedException;
import ca.ntro.core.graphs.writers.NodeSpec;
import ca.ntro.core.graphs.writers.NodeSpecNtro;
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
		options.setDirected(true);
		
		writer.initialize(id, options);
		
		MockNode nodeA = new MockNode("A");
		MockNode nodeAA = new MockNode("AA");
		MockNode nodeAAA = new MockNode("AAA");
		MockNode nodeB = new MockNode("B");
		MockNode nodeC = new MockNode("C");
		
		MockEdge edgeA_B = new MockEdge(nodeA,new EdgeTypeNtro(Direction.FORWARD, "a_b"), nodeB);
		
		ClusterSpec clusterA = new ClusterSpecNtro(nodeA);
		ClusterSpec clusterAA = new ClusterSpecNtro(nodeAA);
		ClusterSpec clusterAAA = new ClusterSpecNtro(nodeAAA);
		NodeSpec _nodeB = new NodeSpecNtro(nodeB);
		NodeSpec _nodeC = new NodeSpecNtro(nodeC);
		
		try {

			writer.addCluster(clusterA);
			writer.addSubCluster(clusterA, clusterAA);
			writer.addSubCluster(clusterAA, clusterAAA);
			writer.addSubNode(clusterAAA,_nodeB);
			writer.addNode(_nodeC);
			//writer.addEdge(_nodeB, new EdgeSpecNtro(edgeA_B), _nodeC);

		} catch (GraphWriterException e) {
			Ntro.exceptionThrower().throwException(e);
		}
		
		writer.writeDot();
		writer.writePng();
	}
	
}
