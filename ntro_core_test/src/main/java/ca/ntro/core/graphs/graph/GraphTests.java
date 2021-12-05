package ca.ntro.core.graphs.graph;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graphs.DirectedEdgeTriple;
import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.EdgeTypeNtro;
import ca.ntro.core.graphs.NodeAlreadyAddedException;
import ca.ntro.core.graphs.NodeIdNtro;
import ca.ntro.core.graphs.SearchStrategy;
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
	public void simpleGraph01() {

		MockGraphBuilder builder = new MockGraphBuilder("simpleGraph01");
		
		MockNodeBuilder nodeA = builder.addNode("A");
		MockNodeBuilder nodeB = builder.addNode("B");
		MockNodeBuilder nodeC = builder.addNode("C");

		MockEdge edgeAB = builder.addEdge(nodeA, "AB", nodeB);
		MockEdge edgeBC = builder.addEdge(nodeB, "BC", nodeC);

		Graph<MockNode, MockEdge, GraphSearchOptionsBuilder> graph = builder.asGraph();

		graph.write(Ntro.graphWriter());
	}
	
	public MockGraphBuilder buildSimpleGraph02(){

		MockGraphBuilder builder = new MockGraphBuilder("simpleGraph02");

		MockNodeBuilder nodeA = builder.addNode("A");

		MockNodeBuilder nodeB = builder.addNode("B");
		MockNodeBuilder nodeC = builder.addNode("C");
		MockNodeBuilder nodeD= builder.addNode("D");

		MockEdge edgeAC = builder.addEdge(nodeA, "AC", nodeC);
		MockEdge edgeBC = builder.addEdge(nodeB, "BC", nodeC);
		MockEdge edgeCD = builder.addEdge(nodeC, "CD", nodeD);
		MockEdge edgeDB = builder.addEdge(nodeD, "DA", nodeA);

		Graph<MockNode, MockEdge, GraphSearchOptionsBuilder> graph = builder.asGraph();
		graph.write(Ntro.graphWriter());
		
		return builder;
	}

	public MockGraphBuilder buildSimpleGraph03(){

		MockGraphBuilder builder = new MockGraphBuilder("simpleGraph03");

		MockNodeBuilder nodeA = builder.addNode("A");

		MockNodeBuilder nodeB = builder.addNode("B");
		MockNodeBuilder nodeC = builder.addNode("C");
		MockNodeBuilder nodeD = builder.addNode("D");
		MockNodeBuilder nodeE = builder.addNode("E");

		MockNodeBuilder nodeF = builder.addNode("F");
		MockNodeBuilder nodeG = builder.addNode("G");
		MockNodeBuilder nodeH = builder.addNode("H");
		MockNodeBuilder nodeI = builder.addNode("I");

		builder.addEdge(nodeA, "AB", nodeB);
		builder.addEdge(nodeA, "AC", nodeC);
		builder.addEdge(nodeA, "AD", nodeD);
		builder.addEdge(nodeA, "AE", nodeE);

		builder.addEdge(nodeE, "EF", nodeF);
		builder.addEdge(nodeE, "EG", nodeG);
		builder.addEdge(nodeE, "EH", nodeH);
		builder.addEdge(nodeE, "EI", nodeI);

		Graph<MockNode, MockEdge, GraphSearchOptionsBuilder> graph = builder.asGraph();
		graph.write(Ntro.graphWriter());
		
		return builder;
	}

	@Test
	public void simpleGraph02() {
		buildSimpleGraph02();
	}

	@Test
	public void nodeAlreadyExists01() {
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();

		MockGraphBuilder builder = new MockGraphBuilder("nodeAlreadyExists01");

		MockNodeBuilder nodeA = builder.addNode("A");
		MockNodeBuilder nodeAA = builder.addNode("A");

		Graph<MockNode, MockEdge, GraphSearchOptionsBuilder> graph = builder.asGraph();

		graph.write(Ntro.graphWriter());

		
		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(NodeAlreadyAddedException.class));
	}

	@Test
	public void nodeAlreadyExists02() throws Throwable {
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();
		
		MockGraphBuilder builder = buildSimpleGraph02();
		builder.addNode("D");

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(NodeAlreadyAddedException.class));
	}


	@Test
	public void reachableEdgesDepthFirst01() throws Throwable {

		MockGraphBuilder builder = buildSimpleGraph03();
		
		GraphSearchOptionsBuilderNtro oneStepOptions = new GraphSearchOptionsBuilderNtro();
		oneStepOptions.setSearchStrategy(SearchStrategy.DEPTH_FIRST_SEARCH);
		oneStepOptions.toSearchOptions().setDirections(new Direction[] {Direction.FORWARD});
		oneStepOptions.setMaxDistance(1);
		
		List<DirectedEdgeTriple<MockNode, MockEdge, GraphSearchOptionsBuilder>> edges = new ArrayList<>();
		
		MockNode nodeA = builder.asGraph().findNode("A");
		MockNode nodeB = builder.asGraph().findNode("B");
		MockNode nodeC = builder.asGraph().findNode("C");
		MockNode nodeD = builder.asGraph().findNode("D");
		MockNode nodeE = builder.asGraph().findNode("E");

		MockNode nodeF = builder.asGraph().findNode("F");
		MockNode nodeG = builder.asGraph().findNode("G");
		MockNode nodeH = builder.asGraph().findNode("H");
		MockNode nodeI = builder.asGraph().findNode("I");

		nodeA.forEachReachableEdge(oneStepOptions, (walkedEdges, edge) -> {
			edges.add(new DirectedEdgeTriple<MockNode,MockEdge,GraphSearchOptionsBuilder>(edge.from(), edge.type(), edge.to()));
		});

		Ntro.asserter().assertEquals(4, edges.size());
		Ntro.asserter().assertTrue("Should contain", edges.contains(new DirectedEdgeTriple<>(nodeA, new EdgeTypeNtro(Direction.FORWARD, "AB"), nodeB)));
		Ntro.asserter().assertTrue("Should contain", edges.contains(new DirectedEdgeTriple<>(nodeA, new EdgeTypeNtro(Direction.FORWARD, "AC"), nodeC)));
		Ntro.asserter().assertTrue("Should contain", edges.contains(new DirectedEdgeTriple<>(nodeA, new EdgeTypeNtro(Direction.FORWARD, "AD"), nodeD)));
		Ntro.asserter().assertTrue("Should contain", edges.contains(new DirectedEdgeTriple<>(nodeA, new EdgeTypeNtro(Direction.FORWARD, "AE"), nodeE)));

		Ntro.asserter().assertFalse("Should not contain", edges.contains(new DirectedEdgeTriple<>(nodeE, new EdgeTypeNtro(Direction.FORWARD, "EF"), nodeF)));
		Ntro.asserter().assertFalse("Should not contain", edges.contains(new DirectedEdgeTriple<>(nodeE, new EdgeTypeNtro(Direction.FORWARD, "EG"), nodeG)));
		Ntro.asserter().assertFalse("Should not contain", edges.contains(new DirectedEdgeTriple<>(nodeE, new EdgeTypeNtro(Direction.FORWARD, "EH"), nodeH)));
		Ntro.asserter().assertFalse("Should not contain", edges.contains(new DirectedEdgeTriple<>(nodeE, new EdgeTypeNtro(Direction.FORWARD, "EI"), nodeI)));
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
