package ca.ntro.core.graphs.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graphs.dag.CycleException;
import ca.ntro.core.graphs.dag.Dag;
import ca.ntro.core.graphs.dag.DagNtro;
import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;
import ca.ntro.core.wrappers.result.Result;

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

		NodeMock nodeA = new NodeMock("A");
		NodeMock nodeB = new NodeMock("B");
		NodeMock nodeC = new NodeMock("C");
		
		EdgeMock edgeAB = new EdgeMock("AB");
		EdgeMock edgeBC = new EdgeMock("BC");
		
		Dag<NodeMock, EdgeMock> dag = new DagNtro<>();
		
		dag.addEdge(nodeA, edgeAB, nodeB);
		dag.addEdge(nodeB, edgeBC, nodeC);
		
		List<NodeMock> nodes = new ArrayList<>();
		dag.forEachNode(n -> {
			nodes.add(n);
		});
		
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeA));
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeB));
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeC));
		Ntro.asserter().assertEquals(3, nodes.size());
		
		List<EdgeTriple> edgeTriples = new ArrayList<>();
		dag.forEachEdge((from, edge, to) -> {
			edgeTriples.add(new EdgeTriple(from, edge, to));
		});

		Ntro.asserter().assertTrue("Should contain", edgeTriples.contains(new EdgeTriple(nodeA, edgeAB, nodeB)));
		Ntro.asserter().assertTrue("Should contain", edgeTriples.contains(new EdgeTriple(nodeB, edgeBC, nodeC)));
		Ntro.asserter().assertEquals(2, edgeTriples.size());
		
		List<NodeMock> reachableFromA = new ArrayList<>();
		dag.forEachReachableNode(nodeA, (distance, n) -> {
			reachableFromA.add(n);
		});

		Ntro.asserter().assertTrue("Should contain", reachableFromA.contains(nodeB));
		Ntro.asserter().assertTrue("Should contain", reachableFromA.contains(nodeC));
		Ntro.asserter().assertEquals(2, reachableFromA.size());
		
		Result<Integer> nodeCount = dag.reduceNodes(0, (accumulator, n) -> {
			return accumulator + 1;
		});

		Ntro.asserter().assertEquals(3, nodeCount.value());

		Result<Integer> edgeCount = dag.recudeEdges(0, (accumulator, from, e, to) -> {
			return accumulator + 1;
		});

		Ntro.asserter().assertEquals(2, edgeCount.value());

		Result<Integer> reachableCount = dag.reduceReachableNodes(nodeA, 0, (accumulator, distance, n) -> {
			return accumulator + 1;
		});

		Ntro.asserter().assertEquals(2, reachableCount.value());
	}

	@Test
	public void simpleGraph02() throws CycleException {

		NodeMock nodeA = new NodeMock("A");
		NodeMock nodeB = new NodeMock("B");
		NodeMock nodeC = new NodeMock("C");
		NodeMock nodeD = new NodeMock("D");
		
		EdgeMock edgeAB = new EdgeMock("AB");
		EdgeMock edgeAC = new EdgeMock("AC");
		EdgeMock edgeBD = new EdgeMock("BD");
		EdgeMock edgeCD = new EdgeMock("CD");
		
		Dag<NodeMock, EdgeMock> dag = new DagNtro<>();
		
		dag.addEdge(nodeA, edgeAB, nodeB);
		dag.addEdge(nodeA, edgeAC, nodeC);
		dag.addEdge(nodeB, edgeBD, nodeD);
		dag.addEdge(nodeC, edgeCD, nodeD);

		List<NodeMock> reachableFromA = new ArrayList<>();
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

		NodeMock nodeA = new NodeMock("A");
		EdgeMock edgeAA = new EdgeMock("AA");


		Dag<NodeMock, EdgeMock> dag = new DagNtro<>();

		dag.addEdge(nodeA, edgeAA, nodeA);
		
		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(CycleException.class));
	}

	@Test
	public void testCycleDetection02() throws CycleException {

		NodeMock nodeA = new NodeMock("A");
		NodeMock nodeB = new NodeMock("B");
		NodeMock nodeC = new NodeMock("C");
		
		EdgeMock edgeAB = new EdgeMock("AB");
		EdgeMock edgeBC = new EdgeMock("BC");
		EdgeMock edgeCA = new EdgeMock("CA");
		
		Dag<NodeMock, EdgeMock> dag = new DagNtro<>();
		
		dag.addEdge(nodeA, edgeAB, nodeB);
		dag.addEdge(nodeB, edgeBC, nodeC);
		dag.addEdge(nodeC, edgeCA, nodeA);

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(CycleException.class));
	}
}
