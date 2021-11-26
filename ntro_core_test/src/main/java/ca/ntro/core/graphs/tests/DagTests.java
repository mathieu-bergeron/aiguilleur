package ca.ntro.core.graphs.tests;


import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graphs.dag.CycleException;
import ca.ntro.core.graphs.dag.DagNtro;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;
import ca.ntro.core.graphs.generic_graph.NodeNtro;
import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;

public class DagTests {

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
	public void simpleGraph01() throws CycleException {

		MockNodeValue nodeValueA = new MockNodeValue("A");
		MockNodeValue nodeValueB = new MockNodeValue("B");
		MockNodeValue nodeValueC = new MockNodeValue("C");
		
		MockEdgeValue edgeValueAB = new MockEdgeValue("AB");
		MockEdgeValue edgeValueBC = new MockEdgeValue("BC");
		
		DagNtro<MockNodeValue, MockEdgeValue, NodeNtro<MockNodeValue>, EdgeNtro<MockEdgeValue>> dag = new DagNtro<>();
		
		/*
		
		Node<MockNodeValue> nodeA = builder.addNode(nodeValueA);
		
		dag.addEdge(nodeValueA, edgeValueAB, nodeValueB);
		dag.addEdge(nodeValueB, edgeValueBC, nodeValueC);
		
		List<MockNodeValue> nodes = new ArrayList<>();
		dag.forEachNode(n -> {
			nodes.add(n);
		});
		
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeValueA));
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeValueB));
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeValueC));
		Ntro.asserter().assertEquals(3, nodes.size());
		
		*/
		
		/*
		
		List<EdgeTriple> edgeTriples = new ArrayList<>();
		dag.forEachEdge((from, edge, to) -> {
			edgeTriples.add(new EdgeTriple(from, edge, to));
		});

		Ntro.asserter().assertTrue("Should contain", edgeTriples.contains(new EdgeTriple(nodeValueA, edgeValueAB, nodeValueB)));
		Ntro.asserter().assertTrue("Should contain", edgeTriples.contains(new EdgeTriple(nodeValueB, edgeValueBC, nodeValueC)));
		Ntro.asserter().assertEquals(2, edgeTriples.size());
		
		List<MockNodeValue> reachableFromA = new ArrayList<>();
		dag.forEachReachableNode(nodeValueA, (distance, n) -> {
			reachableFromA.add(n);
		});

		Ntro.asserter().assertTrue("Should contain", reachableFromA.contains(nodeValueB));
		Ntro.asserter().assertTrue("Should contain", reachableFromA.contains(nodeValueC));
		Ntro.asserter().assertEquals(2, reachableFromA.size());
		
		Result<Integer> nodeCount = dag.reduceNodes(0, (accumulator, n) -> {
			return accumulator + 1;
		});

		Ntro.asserter().assertEquals(3, nodeCount.value());

		Result<Integer> edgeCount = dag.recudeEdges(0, (accumulator, from, e, to) -> {
			return accumulator + 1;
		});

		Ntro.asserter().assertEquals(2, edgeCount.value());

		Result<Integer> reachableCount = dag.reduceReachableNodes(nodeValueA, 0, (accumulator, distance, n) -> {
			return accumulator + 1;
		});

		Ntro.asserter().assertEquals(2, reachableCount.value());
		
		*/
	}
	
	/*

	@Test
	public void simpleGraph02() throws CycleException {

		MockNodeValue nodeA = new MockNodeValue("A");
		MockNodeValue nodeB = new MockNodeValue("B");
		MockNodeValue nodeC = new MockNodeValue("C");
		MockNodeValue nodeD = new MockNodeValue("D");
		
		MockEdgeValue edgeAB = new MockEdgeValue("AB");
		MockEdgeValue edgeAC = new MockEdgeValue("AC");
		MockEdgeValue edgeBD = new MockEdgeValue("BD");
		MockEdgeValue edgeCD = new MockEdgeValue("CD");
		
		Dag<MockNodeValue, MockEdgeValue> dag = new DagNtro<>();
		
		dag.addEdge(nodeA, edgeAB, nodeB);
		dag.addEdge(nodeA, edgeAC, nodeC);
		dag.addEdge(nodeB, edgeBD, nodeD);
		dag.addEdge(nodeC, edgeCD, nodeD);

		List<MockNodeValue> reachableFromA = new ArrayList<>();
		dag.forEachReachableNode(nodeA, (distance, n) -> {
			reachableFromA.add(n);
		});

		Ntro.asserter().assertFalse("Should not contain", reachableFromA.contains(nodeA));
		Ntro.asserter().assertTrue("Should contain", reachableFromA.contains(nodeB));
		Ntro.asserter().assertTrue("Should contain", reachableFromA.contains(nodeC));
		Ntro.asserter().assertTrue("Should contain", reachableFromA.contains(nodeD));
		Ntro.asserter().assertEquals(3, reachableFromA.size());
	}

	@Test
	public void testCycleDetection01() throws CycleException {

		MockNodeValue nodeA = new MockNodeValue("A");
		MockEdgeValue edgeAA = new MockEdgeValue("AA");


		Dag<MockNodeValue, MockEdgeValue> dag = new DagNtro<>();

		dag.addEdge(nodeA, edgeAA, nodeA);
		
		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(CycleException.class));
	}

	@Test
	public void testCycleDetection02() throws CycleException {

		MockNodeValue nodeA = new MockNodeValue("A");
		MockNodeValue nodeB = new MockNodeValue("B");
		MockNodeValue nodeC = new MockNodeValue("C");
		
		MockEdgeValue edgeAB = new MockEdgeValue("AB");
		MockEdgeValue edgeBC = new MockEdgeValue("BC");
		MockEdgeValue edgeCA = new MockEdgeValue("CA");
		
		Dag<MockNodeValue, MockEdgeValue> dag = new DagNtro<>();
		
		dag.addEdge(nodeA, edgeAB, nodeB);
		dag.addEdge(nodeB, edgeBC, nodeC);
		dag.addEdge(nodeC, edgeCA, nodeA);

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(CycleException.class));
	}
	
	*/
}
