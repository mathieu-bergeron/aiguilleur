package ca.ntro.core.graphs.graph;


import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import ca.ntro.core.graphs.DirectedEdgeTriple;
import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.EdgeAlreadyAddedException;
import ca.ntro.core.graphs.EdgeTypeNtro;
import ca.ntro.core.graphs.NodeAlreadyAddedException;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.SearchOptionsNtro;
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
	public void simpleGraph00() throws Throwable {

		GraphBuilder<GraphNode, GraphEdge, GraphSearchOptions> builder = GraphBuilder.newBuilder("simpleGraph00");
		
		GraphNode nodeA = new GraphNode("A");
		GraphNode nodeB = new GraphNode("B");

		builder.addNode(nodeA);
		builder.addNode(nodeB);

		nodeA.addEdge("AB", nodeB);
		
		Graph<GraphNode, GraphEdge, GraphSearchOptions> graph = builder.toGraph();
		graph.write(Ntro.graphWriter());
	}

	@Test
	public void reachableEdgesDepthFirst01() throws Throwable {
		GraphBuilder<GraphNode, GraphEdge, GraphSearchOptions> builder = GraphBuilder.newBuilder("reachableEdgesDepthFirst01");

		GraphNode nodeA = new GraphNode("A");
		GraphNode nodeB = new GraphNode("B");
		GraphNode nodeC = new GraphNode("C");

		builder.addNode(nodeA);
		builder.addNode(nodeB);
		builder.addNode(nodeC);

		nodeA.addEdge("AA", nodeA);
		nodeA.addEdge("AB", nodeB);
		nodeB.addEdge("BC", nodeC);

		Graph<GraphNode, GraphEdge, GraphSearchOptions> graph = builder.toGraph();
		graph.write(Ntro.graphWriter());

		GraphSearchOptions oneStepOptions = new GraphSearchOptions();
		oneStepOptions.setSearchStrategy(SearchStrategy.DEPTH_FIRST_SEARCH);
		oneStepOptions.setDirections(new Direction[] {Direction.FORWARD});
		oneStepOptions.setMaxDistance(1);

		
		List<DirectedEdgeTriple<GraphNode, GraphEdge, GraphSearchOptions>> edges = new ArrayList<>();

		nodeA.forEachReachableEdge(oneStepOptions, (walkedEdges, edge) -> {

			edges.add(new DirectedEdgeTriple<GraphNode,GraphEdge,GraphSearchOptions>(edge.from(), edge.type(), edge.to()));
		});

		Ntro.asserter().assertEquals(2, edges.size());
		Ntro.asserter().assertTrue("Should contain", edges.contains(new DirectedEdgeTriple<>(nodeA, new EdgeTypeNtro(Direction.FORWARD, "AA"), nodeA)));
		Ntro.asserter().assertTrue("Should contain", edges.contains(new DirectedEdgeTriple<>(nodeA, new EdgeTypeNtro(Direction.FORWARD, "AB"), nodeB)));
	}

	@Test
	public void simpleGraph02() throws Throwable {

	}

	@Test
	public void nodeAlreadyAddedException() {
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();

		GraphBuilder<GraphNode,GraphEdge,GraphSearchOptions> builder = GraphBuilder.newBuilder();
		
		GraphNode nodeA = new GraphNode("A");
		GraphNode nodeB = new GraphNode("A");

		builder.addNode(nodeA);
		builder.addNode(nodeB);
		
		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(NodeAlreadyAddedException.class));
	}

	@Test
	public void edgeAlreadyAddedException() {
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();

		GraphBuilder<GraphNode,GraphEdge,GraphSearchOptions> builder = GraphBuilder.newBuilder();
		
		GraphNode nodeA = new GraphNode("A");

		builder.addNode(nodeA);
		
		nodeA.addEdge("AA", nodeA);
		nodeA.addEdge("AA", nodeA);
		
		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(EdgeAlreadyAddedException.class));
	}

	@Ignore
	@Test
	public void edgeAlreadyAddedExceptionUndirected() {
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();

		GraphBuilder<GraphNode,GraphEdge,GraphSearchOptions> builder = GraphBuilder.newBuilder();
		
		GraphNode nodeA = new GraphNode("A");
		GraphNode nodeB = new GraphNode("B");

		builder.addNode(nodeA);
		builder.addNode(nodeB);
		
		nodeA.addEdge("AB", nodeB);
		nodeB.addEdge("AB", nodeA);

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(EdgeAlreadyAddedException.class));
	}
}
