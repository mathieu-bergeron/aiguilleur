package ca.ntro.core.graphs.hierarchical_graph;


import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.graph.GraphEdge;
import ca.ntro.core.graphs.graph.GraphNode;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalGraphBuilder;
import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;

public class HierarchicalGraphTests {
	
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
	public void hierarchicalGraph01() throws Throwable {
		exceptionThrower.clear();

		HierarchicalGraphBuilder<HierarchicalGraphNode, HierarchicalGraphEdge, SearchOptions> builder = HierarchicalGraphBuilder.newBuilder("hierarchicalGraph01");

		HierarchicalGraphNode nodeA = new HierarchicalGraphNode("A");
		HierarchicalGraphNode nodeB = new HierarchicalGraphNode("B");
		HierarchicalGraphNode nodeC = new HierarchicalGraphNode("C");

		HierarchicalGraphNode subNodeAA = new HierarchicalGraphNode("AA");
		HierarchicalGraphNode subNodeAB = new HierarchicalGraphNode("AB");
		HierarchicalGraphNode subNodeAAA = new HierarchicalGraphNode("AAA");

		builder.addNode(nodeA);
		builder.addNode(nodeB);
		builder.addNode(nodeC);

		builder.addNode(subNodeAA);
		builder.addNode(subNodeAAA);
		builder.addNode(subNodeAB);

		nodeA.addEdge("AA", nodeA);
		nodeA.addEdge("AB", nodeB);
		nodeB.addEdge("BC", nodeC);
		
		nodeA.addSubNode(subNodeAA);
		nodeA.addSubNode(subNodeAB);
		
		subNodeAA.addEdge("AA_AB", subNodeAB);

		subNodeAA.addSubNode(subNodeAAA);
		subNodeAAA.addEdge("AAA_C", nodeC);

		HierarchicalGraph<HierarchicalGraphNode, HierarchicalGraphEdge, SearchOptions> graph = builder.toGraph();
		graph.write(Ntro.graphWriter());
		
		/*

		List<Node<MockNodeValue>> nodes = new ArrayList<>();
		graph.forEachNode(n -> {
			nodes.add(n);
		});
		
		Ntro.asserter().assertTrue("Should contain", nodes.contains(node0));
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeA));
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeB));
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeC));
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeAA));
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeAAA));
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeBB));
		Ntro.asserter().assertTrue("Should contain", nodes.contains(nodeBBB));
		Ntro.asserter().assertEquals(8, nodes.size());
		
		List<UndirectedEdgeTriple<MockNodeValue, MockEdgeValue, Node<MockNodeValue>, Edge<MockEdgeValue>>> edges = new ArrayList<>();
		GraphBuilder<MockNodeValue, MockEdgeValue, Node<MockNodeValue>, Edge<MockEdgeValue>> builderTested = GraphBuilder.newBuilder("hierarchicalGraph01_tested");
		*/
		
		/*

		graph.forEachReachableStep(node0, (walkedEdges, step) -> {
			builderTested.addEdge(step.from(), step.edge().value(), step.to());
			edges.add(new UndirectedEdgeTriple<MockNodeValue, MockEdgeValue, Node<MockNodeValue>, Edge<MockEdgeValue>>(step.from(),step.edge(),step.to()));
		});

		builderTested.toGraph().write(Ntro.graphWriter());

		*/
		

		/*
		Ntro.asserter().assertEquals(8, edges.size());
		Ntro.asserter().assertTrue("Should contain", edges.contains(new UndirectedEdgeTriple<>(node0, edge0A, nodeA)));
		Ntro.asserter().assertTrue("Should contain", edges.contains(new UndirectedEdgeTriple<>(nodeA, edgeAB, nodeB)));
		Ntro.asserter().assertTrue("Should contain", edges.contains(new UndirectedEdgeTriple<>(nodeB, edgeBC, nodeC)));
		Ntro.asserter().assertTrue("Should contain", edges.contains(new UndirectedEdgeTriple<>(nodeC, edgeCA, nodeA)));
		Ntro.asserter().assertTrue("Should contain", edges.contains(new UndirectedEdgeTriple<>(nodeAA, edgeAABBB, nodeBBB)));
		Ntro.asserter().assertTrue("Should contain", edges.contains(new UndirectedEdgeTriple<>(nodeAAA, edgeAAABB, nodeBB)));
		*/

		exceptionThrower.throwLastException();
	}
}
