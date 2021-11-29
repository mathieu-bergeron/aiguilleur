package ca.ntro.core.graphs.tests;


import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.EdgeTypeNtro;
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
	public void simpleGraph00() throws Throwable {
		exceptionThrower.clear();

		GraphBuilder<GraphNode, GraphEdge, SearchOptions> builder = GraphBuilder.newBuilder("simpleGraph00");
		
		GraphNode nodeA = new GraphNode("A");
		GraphNode nodeB = new GraphNode("B");

		builder.addNode(nodeA);
		builder.addNode(nodeB);

		nodeA.addEdge("AB", nodeB);

		
		Graph<GraphNode, GraphEdge, SearchOptions> graph = builder.toGraph();
		graph.write(Ntro.graphWriter());
	}

	@Test
	public void reachableEdgesDepthFirst01() throws Throwable {
		exceptionThrower.clear();

		GraphBuilder<GraphNode, GraphEdge, SearchOptions> builder = GraphBuilder.newBuilder("reachableEdgesDepthFirst01");

		GraphNode nodeA = new GraphNode("A");
		GraphNode nodeB = new GraphNode("B");
		GraphNode nodeC = new GraphNode("C");

		builder.addNode(nodeA);
		builder.addNode(nodeB);
		builder.addNode(nodeC);

		nodeA.addEdge("AA", nodeA);
		nodeA.addEdge("AB", nodeB);
		nodeB.addEdge("BC", nodeC);

		Graph<GraphNode, GraphEdge, SearchOptions> graph = builder.toGraph();
		graph.write(Ntro.graphWriter());

		exceptionThrower.throwLastException();

		SearchOptions oneStepOptions = new SearchOptionsNtro(SearchStrategy.DEPTH_FIRST_SEARCH, new Direction[] {Direction.FORWARD}, 1);
		
		List<DirectedEdgeTriple<GraphNode, GraphEdge, SearchOptions>> edges = new ArrayList<>();

		nodeA.forEachReachableEdge(oneStepOptions, (walkedEdges, edge) -> {

			edges.add(new DirectedEdgeTriple<>(edge.from(), edge.type(), edge.to()));
		});

		Ntro.asserter().assertTrue("Should contain", edges.contains(new DirectedEdgeTriple<>(nodeA, new EdgeTypeNtro(Direction.FORWARD, "AA"), nodeB)));
		Ntro.asserter().assertTrue("Should contain", edges.contains(new DirectedEdgeTriple<>(nodeA, new EdgeTypeNtro(Direction.FORWARD, "BB"), nodeA)));
		Ntro.asserter().assertEquals(2, edges.size());
		
		exceptionThrower.throwLastException();
	}

	@Test
	public void simpleGraph02() throws Throwable {
		exceptionThrower.clear();
		
		/*

		GraphBuilder<MockNodeValue, MockEdgeValue, Node<MockNodeValue>, Edge<MockEdgeValue>> builder = GraphBuilder.newBuilder("simpleGraph02");

		MockNodeValue nodeValue0 = new MockNodeValue("0");
		MockNodeValue nodeValueA = new MockNodeValue("A");
		MockNodeValue nodeValueB = new MockNodeValue("B");
		MockNodeValue nodeValueC = new MockNodeValue("C");
		
		MockEdgeValue edgeValue0A = new MockEdgeValue("0A");
		MockEdgeValue edgeValueAB = new MockEdgeValue("AB");
		MockEdgeValue edgeValueBC = new MockEdgeValue("BC");
		MockEdgeValue edgeValueCA = new MockEdgeValue("CA");
		
		Node<MockNodeValue> node0 = builder.addNode(nodeValue0);
		Node<MockNodeValue> nodeA = builder.addNode(nodeValueA);
		Node<MockNodeValue> nodeB = builder.addNode(nodeValueB);
		Node<MockNodeValue> nodeC = builder.addNode(nodeValueC);
		
		Edge<MockEdgeValue> edge0A = builder.addEdge(node0, edgeValue0A, nodeA);
		Edge<MockEdgeValue> edgeAB = builder.addEdge(nodeA, edgeValueAB, nodeB);
		Edge<MockEdgeValue> edgeBC = builder.addEdge(nodeB, edgeValueBC, nodeC);
		Edge<MockEdgeValue> edgeCA = builder.addEdge(nodeC, edgeValueCA, nodeA);
		
		Graph<MockNodeValue, MockEdgeValue, Node<MockNodeValue>, Edge<MockEdgeValue>> graph = builder.toGraph();
		graph.write(Ntro.graphWriter());

		List<Node<MockNodeValue>> nodes = new ArrayList<>();
		graph.forEachNode(n -> {
			nodes.add(n);
		});
		
		Ntro.asserter().assertTrue("Should contain", nodes.contains(node0));
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeA));
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeB));
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeC));
		Ntro.asserter().assertEquals(4, nodes.size());
		
		List<UndirectedEdgeTriple<MockNodeValue, MockEdgeValue, Node<MockNodeValue>, Edge<MockEdgeValue>>> edges = new ArrayList<>();
		GraphBuilder<MockNodeValue, MockEdgeValue, Node<MockNodeValue>, Edge<MockEdgeValue>> builderTested = GraphBuilder.newBuilder("simpleGraph02_tested");

		graph.forEachReachableEdge(node0, (walkedEdges, step) -> {
			builderTested.addEdge(step.from(), step.edge().value(), step.to());
			edges.add(new UndirectedEdgeTriple<>(step.from(),step.edge(),step.to()));
		});
		
		builderTested.toGraph().write(Ntro.graphWriter());

		Ntro.asserter().assertEquals(4, edges.size());
		Ntro.asserter().assertTrue("Should contain", edges.contains(new UndirectedEdgeTriple<>(node0, edge0A, nodeA)));
		Ntro.asserter().assertTrue("Should contain", edges.contains(new UndirectedEdgeTriple<>(nodeA, edgeAB, nodeB)));
		Ntro.asserter().assertTrue("Should contain", edges.contains(new UndirectedEdgeTriple<>(nodeB, edgeBC, nodeC)));
		Ntro.asserter().assertTrue("Should contain", edges.contains(new UndirectedEdgeTriple<>(nodeC, edgeCA, nodeA)));

		exceptionThrower.throwLastException();
		
		
		*/
	}

	@Test
	public void nodeAlreadyAddedException() {
		exceptionThrower.clear();

		GraphBuilder<GraphNode, GraphEdge, SearchOptions> builder = GraphBuilder.newBuilder();
		
		GraphNode nodeA = new GraphNode("A");
		GraphNode nodeB = new GraphNode("A");

		builder.addNode(nodeA);
		builder.addNode(nodeB);
		
		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(NodeAlreadyAddedException.class));
	}

	@Test
	public void edgeAlreadyAddedException() {
		exceptionThrower.clear();
		
		/*

		GraphBuilder<MockNodeValue, MockEdgeValue, Node<MockNodeValue>, Edge<MockEdgeValue>> builder = GraphBuilder.newBuilder();

		MockNodeValue nodeValueA = new MockNodeValue("A");
		MockNodeValue nodeValueB = new MockNodeValue("B");
		
		Node<MockNodeValue> nodeA = builder.addNode(nodeValueA);
		Node<MockNodeValue> nodeB = builder.addNode(nodeValueB);

		MockEdgeValue edgeValueAB = new MockEdgeValue("AB");

		builder.addEdge(nodeA, edgeValueAB, nodeB);
		builder.addEdge(nodeA, edgeValueAB, nodeB);
		
		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(EdgeAlreadyAddedException.class));
		
		*/

	}

	@Test
	public void edgeAlreadyAddedExceptionUndirected() {
		exceptionThrower.clear();
		
		/*

		GraphBuilder<MockNodeValue, MockEdgeValue, Node<MockNodeValue>, Edge<MockEdgeValue>> builder = GraphBuilder.newBuilder();

		MockNodeValue nodeValueA = new MockNodeValue("A");
		MockNodeValue nodeValueB = new MockNodeValue("B");
		
		Node<MockNodeValue> nodeA = builder.addNode(nodeValueA);
		Node<MockNodeValue> nodeB = builder.addNode(nodeValueB);

		MockEdgeValue edgeValueAB = new MockEdgeValue("AB");

		builder.addEdge(nodeA, edgeValueAB, nodeB);
		builder.addEdge(nodeB, edgeValueAB, nodeA);
		
		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(EdgeAlreadyAddedException.class));
		
		*/

	}

}
