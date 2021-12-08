package ca.ntro.core.graphs.graph;


import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graphs.DirectedEdgeTriple;
import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeAlreadyAddedException;
import ca.ntro.core.graphs.common.EdgeTypeNtro;
import ca.ntro.core.graphs.common.NodeAlreadyAddedException;
import ca.ntro.core.graphs.generics.graph.SearchStrategy;
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
		
		GraphBuilder<MockNode, MockEdge> builder = GraphBuilder.newBuilder(MockNode.class, MockEdge.class);
		builder.setGraphName("simpleGraph01");
		
		NodeBuilder<MockNode, MockEdge> nodeA = builder.addNode("A");
		NodeBuilder<MockNode, MockEdge> nodeB = builder.addNode(new MockNode("B"));

		builder.addEdge(nodeA, "AA", nodeA);
		builder.addEdge(nodeA, "AB", nodeB);

		Graph<MockNode, MockEdge> graph = builder.graph();

		graph.write(Ntro.graphWriter());
	}
	
	public GraphBuilder<MockNode, MockEdge> buildSimpleGraph02(){

		GraphBuilder<MockNode, MockEdge> builder = GraphBuilder.newBuilder(MockNode.class, MockEdge.class);
		builder.setGraphName("simpleGraph02");

		NodeBuilder<MockNode,MockEdge> nodeA = builder.addNode("A");

		NodeBuilder<MockNode,MockEdge> nodeB = builder.addNode("B");
		NodeBuilder<MockNode,MockEdge> nodeC = builder.addNode("C");
		NodeBuilder<MockNode,MockEdge> nodeD = builder.addNode("D");

		MockEdge edgeAC = builder.addEdge(nodeA, "AC", nodeC);
		MockEdge edgeBC = builder.addEdge(nodeB, "BC", nodeC);
		MockEdge edgeCD = builder.addEdge(nodeC, "CD", nodeD);
		MockEdge edgeDB = builder.addEdge(nodeD, "DA", nodeA);

		Graph<MockNode, MockEdge> graph = builder.graph();
		graph.write(Ntro.graphWriter());
		
		return builder;
	}
	
	public GraphBuilder<MockNode, MockEdge> buildSimpleGraph03(){

		GraphBuilder<MockNode, MockEdge> builder = GraphBuilder.newBuilder(MockNode.class, MockEdge.class);
		builder.setGraphName("simpleGraph03");

		NodeBuilder<MockNode,MockEdge>  nodeA = builder.addNode("A");

		NodeBuilder<MockNode,MockEdge> nodeB = builder.addNode("B");
		NodeBuilder<MockNode,MockEdge> nodeC = builder.addNode("C");
	    NodeBuilder<MockNode,MockEdge> nodeD = builder.addNode("D");
	    NodeBuilder<MockNode,MockEdge> nodeE = builder.addNode("E");

	    NodeBuilder<MockNode,MockEdge> nodeF = builder.addNode("F");
		NodeBuilder<MockNode,MockEdge> nodeG = builder.addNode("G");
		NodeBuilder<MockNode,MockEdge> nodeH = builder.addNode("H");
		NodeBuilder<MockNode,MockEdge> nodeI = builder.addNode("I");

		builder.addEdge(nodeA, "AB", nodeB);
		builder.addEdge(nodeA, "AC", nodeC);
		builder.addEdge(nodeA, "AD", nodeD);
		builder.addEdge(nodeA, "AE", nodeE);

		builder.addEdge(nodeE, "EF", nodeF);
		builder.addEdge(nodeE, "EG", nodeG);
		builder.addEdge(nodeE, "EH", nodeH);
		builder.addEdge(nodeE, "EI", nodeI);

		Graph<MockNode, MockEdge> graph = builder.graph();
		graph.write(Ntro.graphWriter());
		
		return builder;
	}

	@Test
	public void simpleGraph02() {
		buildSimpleGraph02();
	}

	@Test
	public void simpleGraph03() {
		buildSimpleGraph03();
	}

	@Test
	public void nodeAlreadyExists01() {
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();

		GraphBuilder<MockNode, MockEdge> builder = GraphBuilder.newBuilder(MockNode.class, MockEdge.class);
		builder.setGraphName("nodeAlreadyExists01");

		NodeBuilder<MockNode,MockEdge> nodeA = builder.addNode("A");
		NodeBuilder<MockNode,MockEdge> nodeAA = builder.addNode("A");

		Graph<MockNode, MockEdge> graph = builder.graph();

		graph.write(Ntro.graphWriter());

		
		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(NodeAlreadyAddedException.class));
	}

	@Test
	public void nodeAlreadyExists02() throws Throwable {
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();
		
		GraphBuilder<MockNode,MockEdge> builder = buildSimpleGraph02();
		builder.addNode("D");

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(NodeAlreadyAddedException.class));
	}


	@Test
	public void reachableEdgesDepthFirst01() throws Throwable {

		GraphBuilder<MockNode,MockEdge> builder = buildSimpleGraph03();
		
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
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();

		GraphBuilder<MockNode,MockEdge> builder = GraphBuilder.newBuilder(MockNode.class, MockEdge.class);

		NodeBuilder<MockNode, MockEdge> nodeA = builder.addNode("A");
		NodeBuilder<MockNode, MockEdge> nodeB = builder.addNode("B");
		
		nodeA.addEdge("ab", nodeB);
		nodeA.addEdge("ab", nodeB);
		
		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(EdgeAlreadyAddedException.class));
	}

	@Test
	public void edgeAlreadyAddedExceptionUndirected() {
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();

		GraphBuilder<MockNode,MockEdge> builder = GraphBuilder.newBuilder(MockNode.class, MockEdge.class);
		
		NodeBuilder<MockNode, MockEdge> nodeA = builder.addNode("A");
		NodeBuilder<MockNode, MockEdge> nodeB = builder.addNode("B");

		nodeA.addEdge("ab", nodeB);
		nodeB.addEdge("ab", nodeA);

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(EdgeAlreadyAddedException.class));
	}
}
