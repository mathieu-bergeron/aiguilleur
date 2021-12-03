package ca.ntro.core.graphs.graph;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;

public class GraphTests {
	
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
	public void simpleGraph00() throws Throwable {

		MockGraphBuilder builder = new MockGraphBuilder("simpleGraph00");
		
		MockNode nodeA = builder.addNode("A");
		MockNode nodeB = builder.addNode("B");
		MockNode nodeC = builder.addNode("C");

		MockEdge edgeAB = builder.addEdge(nodeA, "AB", nodeB);
		MockEdge edgeBC = builder.addEdge(nodeB, "BC", nodeC);

		Graph<MockNode, MockEdge, GraphSearchOptionsBuilder> graph = builder.toGraph();

		graph.write(Ntro.graphWriter());
	}

	@Test
	public void simpleGraph01() throws Throwable {

		MockGraphBuilder builder = new MockGraphBuilder("simpleGraph01");

		MockNode nodeA = builder.addNode("A");

		MockNode nodeB = builder.addNode("B");
		MockNode nodeC = builder.addNode("C");
		MockNode nodeD= builder.addNode("D");

		MockEdge edgeAC = builder.addEdge(nodeA, "AC", nodeC);
		MockEdge edgeBC = builder.addEdge(nodeB, "BC", nodeC);
		MockEdge edgeCD = builder.addEdge(nodeC, "CD", nodeD);
		MockEdge edgeDB = builder.addEdge(nodeD, "DA", nodeA);

		Graph<MockNode, MockEdge, GraphSearchOptionsBuilder> graph = builder.toGraph();

		graph.write(Ntro.graphWriter());
	}

	@Test
	public void reachableEdgesDepthFirst01() throws Throwable {
		/*
		
		
		GraphBuilder<MockNode, MockEdge, GraphSearchOptionsBuilder> builder = GraphBuilder.newBuilder("reachableEdgesDepthFirst01");

		MockNode nodeA = new MockNode("A");
		MockNode nodeB = new MockNode("B");
		MockNode nodeC = new MockNode("C");

		builder.addNode(nodeA);
		builder.addNode(nodeB);
		builder.addNode(nodeC);

		nodeA.addEdge("AA", nodeA);
		nodeA.addEdge("AB", nodeB);
		nodeB.addEdge("BC", nodeC);

		Graph<MockNode, MockEdge, GraphSearchOptionsBuilder> graph = builder.toGraph();
		graph.write(Ntro.graphWriter());

		GraphSearchOptionsBuilderNtro oneStepOptions = new GraphSearchOptionsBuilderNtro();
		oneStepOptions.setSearchStrategy(SearchStrategy.DEPTH_FIRST_SEARCH);
		oneStepOptions.toSearchOptions().setDirections(new Direction[] {Direction.FORWARD});
		oneStepOptions.setMaxDistance(1);
		
		List<DirectedEdgeTriple<MockNode, MockEdge, GraphSearchOptionsBuilder>> edges = new ArrayList<>();

		nodeA.forEachReachableEdge(oneStepOptions, (walkedEdges, edge) -> {

			edges.add(new DirectedEdgeTriple<MockNode,MockEdge,GraphSearchOptionsBuilder>(edge.from(), edge.type(), edge.to()));
		});

		Ntro.asserter().assertEquals(2, edges.size());
		Ntro.asserter().assertTrue("Should contain", edges.contains(new DirectedEdgeTriple<>(nodeA, new EdgeTypeNtro(Direction.FORWARD, "AA"), nodeA)));
		Ntro.asserter().assertTrue("Should contain", edges.contains(new DirectedEdgeTriple<>(nodeA, new EdgeTypeNtro(Direction.FORWARD, "AB"), nodeB)));
		*/
	}

	@Test
	public void simpleGraph02() throws Throwable {

	}

	@Test
	public void nodeAlreadyAddedException() {
		/*
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();

		GraphBuilder<MockNode,MockEdge,GraphSearchOptionsBuilder> builder = GraphBuilder.newBuilder();
		
		MockNode nodeA = new MockNode("A");
		MockNode nodeB = new MockNode("A");

		builder.addNode(nodeA);
		builder.addNode(nodeB);
		
		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(NodeAlreadyAddedException.class));
		*/
	}

	@Test
	public void edgeAlreadyAddedException() {
		/*
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();

		GraphBuilder<MockNode,MockEdge,GraphSearchOptionsBuilder> builder = GraphBuilder.newBuilder();
		
		MockNode nodeA = new MockNode("A");

		builder.addNode(nodeA);
		
		nodeA.addEdge("AA", nodeA);
		nodeA.addEdge("AA", nodeA);
		
		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(EdgeAlreadyAddedException.class));
		*/
	}

	@Test
	public void edgeAlreadyAddedExceptionUndirected() {
		/*
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();

		GraphBuilder<MockNode,MockEdge,GraphSearchOptionsBuilder> builder = GraphBuilder.newBuilder();
		
		MockNode nodeA = new MockNode("A");
		MockNode nodeB = new MockNode("B");

		builder.addNode(nodeA);
		builder.addNode(nodeB);
		
		nodeA.addEdge("AB", nodeB);
		nodeB.addEdge("AB", nodeA);

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(EdgeAlreadyAddedException.class));
		*/
	}
}
