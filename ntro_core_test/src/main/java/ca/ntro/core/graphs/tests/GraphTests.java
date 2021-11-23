package ca.ntro.core.graphs.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeAlreadyAddedException;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeAlreadyAddedException;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.SearchStrategy;
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
	public void reachableEdgesDepthFirst01() throws Throwable {
		exceptionThrower.clear();

		GraphBuilder<MockNodeValue, MockEdgeValue> builder = GraphBuilder.newBuilder("reachableEdgesDepthFirst01");

		MockNodeValue nodeValueA = new MockNodeValue("A");
		MockNodeValue nodeValueB = new MockNodeValue("B");

		MockEdgeValue edgeValueAB = new MockEdgeValue("AB");
		
		Node<MockNodeValue> nodeA = builder.addNode(nodeValueA);
		Node<MockNodeValue> nodeB = builder.addNode(nodeValueB);
		
		Edge<MockEdgeValue> edgeAB = builder.addEdge(nodeA, edgeValueAB, nodeB);

		Graph<MockNodeValue, MockEdgeValue> graph = builder.toGraph();
		graph.write(Ntro.graphWriter());

		SearchOptions oneStepOptions = new SearchOptionsNtro(SearchStrategy.DEPTH_FIRST_SEARCH, new Direction[] {Direction.FORWARD}, 1);
		
		List<EdgeTriple> edges = new ArrayList<>();
		graph.forEachReachableEdge(nodeA, oneStepOptions, (walkedEdges, from, edge, to) -> {
			edges.add(new EdgeTriple(from.value(),edge.value(),to.value()));
		});

		Ntro.asserter().assertTrue("Should contain", edges.contains(new EdgeTriple(nodeValueA, edgeValueAB, nodeValueB)));
		Ntro.asserter().assertEquals(1, edges.size());
		
		
		exceptionThrower.throwLastException();
	}

	@Test
	public void simpleGraph02() throws Throwable {
		exceptionThrower.clear();
		
		GraphBuilder<MockNodeValue, MockEdgeValue> builder = GraphBuilder.newBuilder("simpleGraph02");

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
		graph.write(Ntro.graphWriter());

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


		exceptionThrower.throwLastException();
	}

	@Test
	public void nodeAlreadyAddedException() {
		exceptionThrower.clear();

		GraphBuilder<MockNodeValue, MockEdgeValue> builder = GraphBuilder.newBuilder();

		MockNodeValue nodeValueA = new MockNodeValue("A");
		
		builder.addNode(nodeValueA);
		builder.addNode(nodeValueA);
		
		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(NodeAlreadyAddedException.class));

	}

	@Test
	public void edgeAlreadyAddedException() {
		exceptionThrower.clear();

		GraphBuilder<MockNodeValue, MockEdgeValue> builder = GraphBuilder.newBuilder();

		MockNodeValue nodeValueA = new MockNodeValue("A");
		MockNodeValue nodeValueB = new MockNodeValue("B");
		
		Node<MockNodeValue> nodeA = builder.addNode(nodeValueA);
		Node<MockNodeValue> nodeB = builder.addNode(nodeValueB);

		MockEdgeValue edgeValueAB = new MockEdgeValue("AB");

		builder.addEdge(nodeA, edgeValueAB, nodeB);
		builder.addEdge(nodeA, edgeValueAB, nodeB);
		
		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(EdgeAlreadyAddedException.class));

	}

	@Test
	public void edgeAlreadyAddedExceptionUndirected() {
		exceptionThrower.clear();

		GraphBuilder<MockNodeValue, MockEdgeValue> builder = GraphBuilder.newBuilder();

		MockNodeValue nodeValueA = new MockNodeValue("A");
		MockNodeValue nodeValueB = new MockNodeValue("B");
		
		Node<MockNodeValue> nodeA = builder.addNode(nodeValueA);
		Node<MockNodeValue> nodeB = builder.addNode(nodeValueB);

		MockEdgeValue edgeValueAB = new MockEdgeValue("AB");

		builder.addEdge(nodeA, edgeValueAB, nodeB);
		builder.addEdge(nodeB, edgeValueAB, nodeA);
		
		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(EdgeAlreadyAddedException.class));

	}

}
