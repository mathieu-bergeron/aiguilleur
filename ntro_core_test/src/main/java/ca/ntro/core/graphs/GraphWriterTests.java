package ca.ntro.core.graphs;



import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graphs.generics.generic_graph.Direction;
import ca.ntro.core.graphs.generics.generic_graph.EdgeTypeNtro;
import ca.ntro.core.graphs.generics.generic_graph.GraphId;
import ca.ntro.core.graphs.generics.generic_graph.NodeNotFoundException;
import ca.ntro.core.graphs.graph.MockEdge;
import ca.ntro.core.graphs.graph.MockNode;
import ca.ntro.core.graphs.graph_writer.ClusterNotFoundException;
import ca.ntro.core.graphs.graph_writer.ClusterSpec;
import ca.ntro.core.graphs.graph_writer.ClusterSpecNtro;
import ca.ntro.core.graphs.graph_writer.EdgeSpecNtro;
import ca.ntro.core.graphs.graph_writer.GraphWriter;
import ca.ntro.core.graphs.graph_writer.GraphWriterException;
import ca.ntro.core.graphs.graph_writer.GraphWriterOptionsNtro;
import ca.ntro.core.graphs.graph_writer.NodeSpec;
import ca.ntro.core.graphs.graph_writer.NodeSpecNtro;
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
		MockNode nodeB = new MockNode("B");
		MockNode nodeC = new MockNode("C");
		MockNode nodeD = new MockNode("D");

		MockNode nodeF = new MockNode("F");
		MockNode nodeFF = new MockNode("FF");

		MockNode nodeE = new MockNode("E");
		
		MockEdge edgeBC = new MockEdge(nodeB,new EdgeTypeNtro(Direction.FORWARD, "BC"), nodeC);
		MockEdge edgeBD = new MockEdge(nodeB,new EdgeTypeNtro(Direction.FORWARD, "BD"), nodeD);

		MockEdge edgeA_AA = new MockEdge(nodeA,new EdgeTypeNtro(Direction.FORWARD, "A_AA"), nodeAA);
		MockEdge edgeA_FF = new MockEdge(nodeA,new EdgeTypeNtro(Direction.FORWARD, "A_FF"), nodeFF);
		MockEdge edgeB_E = new MockEdge(nodeB,new EdgeTypeNtro(Direction.FORWARD, "B_E"), nodeE);
		
		ClusterSpec clusterA = new ClusterSpecNtro(nodeA);
		ClusterSpec clusterAA = new ClusterSpecNtro(nodeAA);

		ClusterSpec clusterF = new ClusterSpecNtro(nodeF);
		ClusterSpec clusterFF = new ClusterSpecNtro(nodeFF);

		NodeSpec _nodeB = new NodeSpecNtro(nodeB);
		NodeSpec _nodeC = new NodeSpecNtro(nodeC);
		NodeSpec _nodeD = new NodeSpecNtro(nodeD);
		NodeSpec _nodeE = new NodeSpecNtro(nodeE);

		try {

			writer.addCluster(clusterA);
			writer.addSubCluster(clusterA, clusterAA);

			writer.addCluster(clusterF);
			writer.addSubCluster(clusterF, clusterFF);
			
			writer.addSubNode(clusterAA,_nodeB);
			writer.addSubNode(clusterAA,_nodeD);

			writer.addSubNode(clusterFF,_nodeE);

			writer.addNode(_nodeC);

		 	writer.addEdge(_nodeB, new EdgeSpecNtro(edgeBC), _nodeC);
		 	writer.addEdge(_nodeB, new EdgeSpecNtro(edgeBD), _nodeD);

		 	//writer.addEdge(clusterA, new EdgeSpecNtro(edgeA_AA), clusterAA);
		 	writer.addEdge(_nodeB, new EdgeSpecNtro(edgeB_E), _nodeE);
		 	writer.addEdge(clusterA, new EdgeSpecNtro(edgeA_FF), clusterFF);

		} catch (GraphWriterException e) {
			Ntro.exceptionThrower().throwException(e);
		}
		
		writer.writeDot();
		writer.writePng();
	}

	
}
