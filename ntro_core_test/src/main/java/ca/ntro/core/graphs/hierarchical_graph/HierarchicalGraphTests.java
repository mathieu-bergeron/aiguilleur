package ca.ntro.core.graphs.hierarchical_graph;


import java.util.ArrayList;
import java.util.List;

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

		HierarchicalGraphBuilder<HierarchicalGraphNode, HierarchicalGraphEdge, HierarchicalGraphSearchOptions> builder = HierarchicalGraphBuilder.newBuilder("hierarchicalGraph01");

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

		HierarchicalGraph<HierarchicalGraphNode, HierarchicalGraphEdge, HierarchicalGraphSearchOptions> graph = builder.toGraph();

		Ntro.asserter().assertTrue("nodeA has subNodes", nodeA.hasSubNodes());
		Ntro.asserter().assertFalse("nodeB does not have subNodes", nodeB.hasSubNodes());
		
		List<HierarchicalGraphNode> subNodes = new ArrayList<>();
		
		nodeA.forEachSubNode((walked, subNode) -> {
			subNodes.add(subNode);
		});
		
		Ntro.asserter().assertEquals(3, subNodes.size());

		List<HierarchicalGraphNode> allNodes = new ArrayList<>();
		
		graph.forEachNode(n -> {
			allNodes.add(n);
		});
		
		Ntro.asserter().assertEquals(6, allNodes.size());

		List<HierarchicalGraphNode> clusters = new ArrayList<>();
		
		graph.forEachNode(n -> {
			if(n.hasSubNodes()) {
				clusters.add(n);
			}
		});
		
		Ntro.asserter().assertEquals(2, clusters.size());

		graph.write(Ntro.graphWriter());

		exceptionThrower.throwLastException();
	}
}
