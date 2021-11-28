package ca.ntro.core.graphs.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.graph.GraphBuilder;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalGraph;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalGraphBuilder;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNode;
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
		
		/*a

		HierarchicalGraphBuilder<MockNodeValue, 
		                         MockEdgeValue, 
		                         HierarchicalNode<MockNodeValue, MockEdgeValue, ?,?>, 
		                         Edge<MockEdgeValue>> builder = HierarchicalGraphBuilder.newBuilder("hierarchicalGraph01");

		MockNodeValue nodeValue0 = new MockNodeValue("0");
		MockNodeValue nodeValueA = new MockNodeValue("A");
		MockNodeValue nodeValueB = new MockNodeValue("B");
		MockNodeValue nodeValueC = new MockNodeValue("C");
		
		MockNodeValue nodeValueAA = new MockNodeValue("AA");
		MockNodeValue nodeValueAAA = new MockNodeValue("AAA");
		MockNodeValue nodeValueBB = new MockNodeValue("BB");
		MockNodeValue nodeValueBBB = new MockNodeValue("BBB");
		
		MockEdgeValue edgeValue0A = new MockEdgeValue("0A");
		MockEdgeValue edgeValueAB = new MockEdgeValue("AB");
		MockEdgeValue edgeValueBC = new MockEdgeValue("BC");
		MockEdgeValue edgeValueCA = new MockEdgeValue("CA");

		MockEdgeValue edgeValueAABBB = new MockEdgeValue("AABBB");
		MockEdgeValue edgeValueAAABB = new MockEdgeValue("AAABB");
		
		HierarchicalNode<?,?,?,?> node0 = builder.addNode(nodeValue0);
		HierarchicalNode<?,?,?,?> nodeA = builder.addNode(nodeValueA);
		HierarchicalNode<?,?,?,?> nodeB = builder.addNode(nodeValueB);
		HierarchicalNode<?,?,?,?> nodeC = builder.addNode(nodeValueC);

		HierarchicalNode<?,?,?,?> nodeAA = builder.addNode(nodeValueAA);
		HierarchicalNode<?,?,?,?> nodeAAA = builder.addNode(nodeValueAAA);
		HierarchicalNode<?,?,?,?> nodeBB = builder.addNode(nodeValueBB);
		HierarchicalNode<?,?,?,?> nodeBBB = builder.addNode(nodeValueBBB);
		
		Edge<MockEdgeValue> edge0A = builder.addEdge(node0, edgeValue0A, nodeA);
		Edge<MockEdgeValue> edgeAB = builder.addEdge(nodeA, edgeValueAB, nodeB);
		Edge<MockEdgeValue> edgeBC = builder.addEdge(nodeB, edgeValueBC, nodeC);
		Edge<MockEdgeValue> edgeCA = builder.addEdge(nodeC, edgeValueCA, nodeA);

		Edge<MockEdgeValue> edgeAABBB = builder.addEdge(nodeAA, edgeValueAABBB, nodeBBB);
		Edge<MockEdgeValue> edgeAAABB = builder.addEdge(nodeAAA, edgeValueAAABB, nodeBB);
		
		HierarchicalGraph<MockNodeValue, 
		                  MockEdgeValue, 
		                  HierarchicalNode<MockNodeValue, MockEdgeValue, ?, Edge<MockEdgeValue>>, 
		                  Edge<MockEdgeValue>> graph = builder.toGraph();

		graph.write(Ntro.graphWriter());

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
