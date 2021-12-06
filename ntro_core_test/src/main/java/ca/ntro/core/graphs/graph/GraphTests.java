package ca.ntro.core.graphs.graph;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graphs.DirectedEdgeTriple;
import ca.ntro.core.graphs.generics.directed_graph.Direction;
import ca.ntro.core.graphs.generics.directed_graph.EdgeTypeNtro;
import ca.ntro.core.graphs.generics.directed_graph.NodeAlreadyAddedException;
import ca.ntro.core.graphs.generics.directed_graph.SearchStrategy;
import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilderNtro;
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
		
		MockNode nodeA = builder.addNode("A");
		MockNode nodeB = builder.addNode("B");
		MockNode nodeC = builder.addNode("C");

		MockEdge edgeAB = builder.addEdge(nodeA, "AB", nodeB);
		MockEdge edgeBC = builder.addEdge(nodeB, "BC", nodeC);

		GenericGraph<MockNode, MockEdge, GraphSearchOptionsBuilder> graph = builder.graph();

		graph.write(Ntro.graphWriter());
	}
	
	public MockGraphBuilder buildSimpleGraph02(){

		MockGraphBuilder builder = new MockGraphBuilder("simpleGraph02");

		MockNode nodeA = builder.addNode("A");

		MockNode nodeB = builder.addNode("B");
		MockNode nodeC = builder.addNode("C");
		MockNode nodeD= builder.addNode("D");

		MockEdge edgeAC = builder.addEdge(nodeA, "AC", nodeC);
		MockEdge edgeBC = builder.addEdge(nodeB, "BC", nodeC);
		MockEdge edgeCD = builder.addEdge(nodeC, "CD", nodeD);
		MockEdge edgeDB = builder.addEdge(nodeD, "DA", nodeA);

		GenericGraph<MockNode, MockEdge, GraphSearchOptionsBuilder> graph = builder.graph();
		graph.write(Ntro.graphWriter());
		
		return builder;
	}

	public MockGraphBuilder buildSimpleGraph03(){

		MockGraphBuilder builder = new MockGraphBuilder("simpleGraph03");

		MockNode nodeA = builder.addNode("A");

		MockNode nodeB = builder.addNode("B");
		MockNode nodeC = builder.addNode("C");
		MockNode nodeD = builder.addNode("D");
		MockNode nodeE = builder.addNode("E");

		MockNode nodeF = builder.addNode("F");
		MockNode nodeG = builder.addNode("G");
		MockNode nodeH = builder.addNode("H");
		MockNode nodeI = builder.addNode("I");

		builder.addEdge(nodeA, "AB", nodeB);
		builder.addEdge(nodeA, "AC", nodeC);
		builder.addEdge(nodeA, "AD", nodeD);
		builder.addEdge(nodeA, "AE", nodeE);

		builder.addEdge(nodeE, "EF", nodeF);
		builder.addEdge(nodeE, "EG", nodeG);
		builder.addEdge(nodeE, "EH", nodeH);
		builder.addEdge(nodeE, "EI", nodeI);

		GenericGraph<MockNode, MockEdge, GraphSearchOptionsBuilder> graph = builder.graph();
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

		MockNode nodeA = builder.addNode("A");
		MockNode nodeAA = builder.addNode("A");

		GenericGraph<MockNode, MockEdge, GraphSearchOptionsBuilder> graph = builder.graph();

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
		oneStepOptions.internal().setDirections(new Direction[] {Direction.FORWARD});
		oneStepOptions.setMaxDistance(1);
		
		List<DirectedEdgeTriple<MockNode, MockEdge, GraphSearchOptionsBuilder>> edges = new ArrayList<>();
		
		MockNode nodeA = builder.graph().findNode("A");
		MockNode nodeB = builder.graph().findNode("B");
		MockNode nodeC = builder.graph().findNode("C");
		MockNode nodeD = builder.graph().findNode("D");
		MockNode nodeE = builder.graph().findNode("E");

		MockNode nodeF = builder.graph().findNode("F");
		MockNode nodeG = builder.graph().findNode("G");
		MockNode nodeH = builder.graph().findNode("H");
		MockNode nodeI = builder.graph().findNode("I");

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
