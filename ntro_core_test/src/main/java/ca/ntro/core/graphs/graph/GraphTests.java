package ca.ntro.core.graphs.graph;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ca.ntro.core.graphs.DirectedEdgeTriple;
import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeAlreadyAddedException;
import ca.ntro.core.graphs.common.EdgeTypeNtro;
import ca.ntro.core.graphs.common.NodeAlreadyAddedException;
import ca.ntro.core.graphs.generics.graph.InternalSearchOptionsNtro;
import ca.ntro.core.graphs.generics.graph.SearchStrategy;
import ca.ntro.core.graphs.generics.graph.VisitedNode;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.tests.NtroTests;
import ca.ntro.core.wrappers.optionnal.Optionnal;

public class GraphTests extends NtroTests {

	@Test
	public void searchOptions() {
		
		InternalSearchOptionsNtro internalOptions = new InternalSearchOptionsNtro();
		internalOptions.setDirections(new Direction[] {Direction.FORWARD, Direction.BACKWARD, Direction.UP, Direction.DOWN});
		internalOptions.setMaxDistance(Optionnal.fromValue(10));
		
		GraphSearchOptionsNtro options = new GraphSearchOptionsNtro();
		options.copyOptions(internalOptions);
		
		Ntro.asserter().assertArrayEquals(options.internal().directions(), new Direction[] {Direction.FORWARD, Direction.BACKWARD, Direction.UP, Direction.DOWN});
		Ntro.asserter().assertEquals(10, options.internal().getMaxDistance().value());
	}

	@Test
	public void simpleGraph00() {
		
		GraphBuilder<MockNode, MockEdge> builder = GraphBuilder.newBuilder(MockNode.class, MockEdge.class);
		builder.setGraphName("simpleGraph00");
		
		NodeBuilder<MockNode, MockEdge> nodeBuilderA = builder.addNode("A");
		NodeBuilder<MockNode, MockEdge> nodeBuilderB = builder.addNode(new MockNode("B"));
		NodeBuilder<MockNode, MockEdge> nodeBuilderC = builder.addNode("C");
		
		MockNode nodeA = builder.graph().findNode("A");
		MockNode nodeB = builder.graph().findNode("B");
		MockNode nodeC = builder.graph().findNode("C");
		
		MockEdge edgeAA = builder.addEdge(nodeBuilderA, "AA", nodeBuilderA);
		MockEdge edgeAB = builder.addEdge(nodeBuilderA, "AB", nodeBuilderB);
		MockEdge edgeBC = builder.addEdge(nodeBuilderB, "BC", nodeBuilderC);

		Graph<MockNode, MockEdge> graph = builder.graph();

		Ntro.asserter().assertEquals(3, nodeA.reachableNodes().size());
		Ntro.asserter().assertEquals(3, nodeA.reachableEdges().size());
		
		Ntro.asserter().assertTrue("Reaches B", nodeA.reachableNodes().ifSome(vn -> vn.node().equals(nodeB)));
		Ntro.asserter().assertTrue("Reaches A", nodeA.reachableNodes().ifSome(vn -> vn.node().equals(nodeA)));
		Ntro.asserter().assertTrue("Reaches C", nodeA.reachableNodes().ifSome(vn -> vn.node().equals(nodeC)));
		Ntro.asserter().assertTrue("Reaches AB", nodeA.reachableEdges().ifSome(vn -> vn.edge().equals(edgeAB)));
		Ntro.asserter().assertTrue("Reaches BC", nodeA.reachableEdges().ifSome(vn -> vn.edge().equals(edgeBC)));
		
		Ntro.asserter().assertTrue("Contains A", graph.nodes().ifSome(n -> n.equals(nodeA)));
		Ntro.asserter().assertTrue("Contains B", graph.nodes().ifSome(n -> n.equals(nodeB)));
		Ntro.asserter().assertTrue("Contains C", graph.nodes().ifSome(n -> n.equals(nodeC)));
		Ntro.asserter().assertTrue("Conatins AA", graph.edges().ifSome(e -> e.equals(edgeAA)));
		Ntro.asserter().assertTrue("Conatins AB", graph.edges().ifSome(e -> e.equals(edgeAB)));
		Ntro.asserter().assertTrue("Conatins BC", graph.edges().ifSome(e -> e.equals(edgeBC)));

		Ntro.asserter().assertEquals(3, graph.nodes().size());
		Ntro.asserter().assertEquals(3, graph.edges().size());

		graph.write(Ntro.graphWriter());
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

		builder.addEdge(nodeB, "BF", nodeF);
		builder.addEdge(nodeB, "BG", nodeG);
		builder.addEdge(nodeB, "BH", nodeH);
		builder.addEdge(nodeB, "BI", nodeI);

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

		NodeBuilder<MockNode,MockEdge> nodeA = builder.addNode("A");
		NodeBuilder<MockNode,MockEdge> nodeAA = builder.addNode("A");

		Graph<MockNode, MockEdge> graph = builder.graph();

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
		
		GraphSearchOptionsNtro oneStepOptions = new GraphSearchOptionsNtro();
		oneStepOptions.setSearchStrategy(SearchStrategy.DEPTH_FIRST_SEARCH);
		oneStepOptions.internal().setDirections(new Direction[] {Direction.FORWARD});
		oneStepOptions.setMaxDistance(1);
		
		List<DirectedEdgeTriple<MockNode, MockEdge, GraphSearchOptions>> edges = new ArrayList<>();
		
		MockNode nodeA = builder.graph().findNode("A");
		MockNode nodeB = builder.graph().findNode("B");
		MockNode nodeC = builder.graph().findNode("C");
		MockNode nodeD = builder.graph().findNode("D");
		MockNode nodeE = builder.graph().findNode("E");
		MockNode nodeF = builder.graph().findNode("F");
		MockNode nodeG = builder.graph().findNode("G");
		MockNode nodeH = builder.graph().findNode("H");
		MockNode nodeI = builder.graph().findNode("I");

		nodeA.edges(oneStepOptions).forEach(edge -> {
			edges.add(new DirectedEdgeTriple<MockNode,MockEdge,GraphSearchOptions>(edge.from(), edge.type(), edge.to()));
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
	public void reachableEdgesBreadthFirst01() throws Throwable {

		GraphBuilder<MockNode,MockEdge> builder = buildSimpleGraph03();
		
		GraphSearchOptionsNtro oneStepOptions = new GraphSearchOptionsNtro();
		oneStepOptions.setSearchStrategy(SearchStrategy.BREADTH_FIRST_SEARCH);
		oneStepOptions.internal().setDirections(new Direction[] {Direction.FORWARD});
		oneStepOptions.internal().setSortEdgesByName(true);
		
		List<DirectedEdgeTriple<MockNode, MockEdge, GraphSearchOptions>> edges = new ArrayList<>();
		
		MockNode nodeA = builder.graph().findNode("A");
		MockNode nodeB = builder.graph().findNode("B");
		MockNode nodeC = builder.graph().findNode("C");
		MockNode nodeD = builder.graph().findNode("D");
		MockNode nodeE = builder.graph().findNode("E");
		MockNode nodeF = builder.graph().findNode("F");
		MockNode nodeG = builder.graph().findNode("G");
		MockNode nodeH = builder.graph().findNode("H");
		MockNode nodeI = builder.graph().findNode("I");
		
		Stream<VisitedNode<MockNode, MockEdge, GraphSearchOptions>> visitedNodesStream = nodeA.reachableNodes(oneStepOptions);
		List<MockNode> visitedNodes = visitedNodesStream.map(vn -> vn.node()).collect();

		//Ntro.asserter().assertEquals(8, visitedNodes.size());
		Ntro.asserter().assertTrue("visitedNode.get(0) == B", visitedNodes.get(0) == nodeB);
		Ntro.asserter().assertTrue("visitedNode.get(1) == C", visitedNodes.get(1) == nodeC);
		Ntro.asserter().assertTrue("visitedNode.get(2) == D", visitedNodes.get(2) == nodeD);
		Ntro.asserter().assertTrue("visitedNode.get(3) == E", visitedNodes.get(3) == nodeE);
		//Ntro.asserter().assertTrue("visitedNode.get(4) == F", visitedNodes.get(4) == nodeF);
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
