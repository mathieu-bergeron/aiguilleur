package ca.ntro.core.graphs.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.graph.Graph;
import ca.ntro.core.graphs.graph.GraphBuilder;
import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;

public class GraphTests {

	private static ExceptionThrowerMock exceptionThrower = new ExceptionThrowerMock();
	
	protected ExceptionThrowerMock exceptionThrower() {
		return exceptionThrower;
	}

	@BeforeClass
	public static void initialize() {
		InitializerTest.initialize();
		InitializerTest.registerExceptionThrower(exceptionThrower);
	}

	@Test
	public void simpleGraph01() {
		
		GraphBuilder<MockNodeValue, MockEdgeValue> builder = GraphBuilder.newBuilder();

		MockNodeValue nodeValueA = new MockNodeValue("A");
		MockNodeValue nodeValueB = new MockNodeValue("B");
		MockNodeValue nodeValueC = new MockNodeValue("C");
		
		MockEdgeValue edgeValueAB = new MockEdgeValue("AB");
		MockEdgeValue edgeValueBC = new MockEdgeValue("BC");
		
		Node<MockNodeValue> nodeA = builder.addNode(nodeValueA);
		Node<MockNodeValue> nodeB = builder.addNode(nodeValueB);
		Node<MockNodeValue> nodeC = builder.addNode(nodeValueC);
		
		Edge<MockEdgeValue> edgeAB = builder.addEdge(nodeA, edgeValueAB, nodeB);
		Edge<MockEdgeValue> edgeBC = builder.addEdge(nodeB, edgeValueBC, nodeC);
		
		Graph<MockNodeValue, MockEdgeValue> graph = builder.toGraph();
		
		List<Node<MockNodeValue>> nodes = new ArrayList<>();
		graph.forEachNode(n -> {
			nodes.add(n);
		});
		
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeA));
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeB));
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeC));
		Ntro.asserter().assertEquals(3, nodes.size());
		
		List<Edge<MockEdgeValue>> edges = new ArrayList<>();
		graph.forEachEdge((from, e, to) -> {
			edges.add(e);
		});

		Ntro.asserter().assertTrue("Should contain", edges.contains(edgeAB));
		Ntro.asserter().assertTrue("Should contain", edges.contains(edgeBC));
		Ntro.asserter().assertEquals(2, edges.size());
	}

}
