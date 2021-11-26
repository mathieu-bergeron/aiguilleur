package ca.ntro.core.graphs.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;
import ca.ntro.core.graphs.generic_graph.NodeNtro;
import ca.ntro.core.graphs.graph.GraphBuilder;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalGraph;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalGraphBuilder;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNodeNtro;
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

		HierarchicalGraphBuilder<MockNodeValue, 
		                         MockEdgeValue, 
		                         HierarchicalNodeNtro<MockNodeValue>, 
		                         EdgeNtro<MockEdgeValue>> builder = HierarchicalGraphBuilder.newBuilder("hierarchicalGraph01");

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
		
		HierarchicalGraph<MockNodeValue, 
		                  MockEdgeValue, 
		                  HierarchicalNodeNtro<MockNodeValue>, 
		                  EdgeNtro<MockEdgeValue>> graph = builder.toGraph();

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
		
		List<UndirectedEdgeTriple<MockNodeValue, MockEdgeValue>> edges = new ArrayList<>();
		GraphBuilder<MockNodeValue, MockEdgeValue, NodeNtro<MockNodeValue>, EdgeNtro<MockEdgeValue>> builderTested = GraphBuilder.newBuilder("simpleGraph02_tested");

		graph.forEachReachableStep(node0, (walkedEdges, step) -> {
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
	}
}
