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

		MockEdge edgeAB = builder.addEdge(nodeA.toNode(), "AB", nodeB.toNode());
		MockEdge edgeBC = builder.addEdge(nodeB.toNode(), "BC", nodeC.toNode());

		Graph<MockNode, MockEdge, GraphSearchOptionsBuilder> graph = builder.toGraph();

		graph.write(Ntro.graphWriter());
	}
	
	public MockGraphBuilder buildSimpleGraph02(){

		MockGraphBuilder builder = new MockGraphBuilder("simpleGraph02");

		MockNodeBuilder nodeA = builder.addNode("A");

		MockNodeBuilder nodeB = builder.addNode("B");
		MockNodeBuilder nodeC = builder.addNode("C");
		MockNodeBuilder nodeD= builder.addNode("D");

		MockEdge edgeAC = builder.addEdge(nodeA.toNode(), "AC", nodeC.toNode());
		MockEdge edgeBC = builder.addEdge(nodeB.toNode(), "BC", nodeC.toNode());
		MockEdge edgeCD = builder.addEdge(nodeC.toNode(), "CD", nodeD.toNode());
		MockEdge edgeDB = builder.addEdge(nodeD.toNode(), "DA", nodeA.toNode());

		Graph<MockNode, MockEdge, GraphSearchOptionsBuilder> graph = builder.toGraph();
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

		builder.addEdge(nodeA.toNode(), "AB", nodeB.toNode());
		builder.addEdge(nodeA.toNode(), "AC", nodeC.toNode());
		builder.addEdge(nodeA.toNode(), "AD", nodeD.toNode());
		builder.addEdge(nodeA.toNode(), "AE", nodeE.toNode());

		builder.addEdge(nodeE.toNode(), "EF", nodeF.toNode());
		builder.addEdge(nodeE.toNode(), "EG", nodeG.toNode());
		builder.addEdge(nodeE.toNode(), "EH", nodeH.toNode());
		builder.addEdge(nodeE.toNode(), "EI", nodeI.toNode());

		Graph<MockNode, MockEdge, GraphSearchOptionsBuilder> graph = builder.toGraph();
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

		Graph<MockNode, MockEdge, GraphSearchOptionsBuilder> graph = builder.toGraph();

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
		
		MockNode nodeA = builder.toGraph().findNode("A");
		MockNode nodeB = builder.toGraph().findNode("B");
		MockNode nodeC = builder.toGraph().findNode("C");
		MockNode nodeD = builder.toGraph().findNode("D");
		MockNode nodeE = builder.toGraph().findNode("E");

		MockNode nodeF = builder.toGraph().findNode("F");
		MockNode nodeG = builder.toGraph().findNode("G");
		MockNode nodeH = builder.toGraph().findNode("H");
		MockNode nodeI = builder.toGraph().findNode("I");

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
