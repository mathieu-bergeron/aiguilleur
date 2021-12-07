package ca.ntro.core.graphs.hierarchical_graph;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;

public class HierarchicalGraphTests {

	protected ExceptionThrowerMock registerMockExceptionThrower() {
		ExceptionThrowerMock exceptionThrowerMock = new ExceptionThrowerMock();

		InitializerTest.registerExceptionThrower(exceptionThrowerMock);

		return exceptionThrowerMock;
	}

	@BeforeClass
	public static void initialize() {
		InitializerTest.initialize();
	}

	private HierarchicalGraphBuilder<MockHierarchicalNode, MockHierarchicalEdge> newBuilder(){
		return HierarchicalGraphBuilder.newBuilder(MockHierarchicalNode.class, MockHierarchicalEdge.class);
	}
	
	private void buildGraph00(HierarchicalGraphBuilder<MockHierarchicalNode, MockHierarchicalEdge> builder){

		HierarchicalGraphNodeBuilder<MockHierarchicalNode, MockHierarchicalEdge> nodeA = builder.addNode("A");
		HierarchicalGraphNodeBuilder<MockHierarchicalNode, MockHierarchicalEdge> nodeB = builder.addNode("B");
		
		builder.addEdge(nodeA, "AB", nodeB);
	}

	private void buildGraph01(HierarchicalGraphBuilder<MockHierarchicalNode, MockHierarchicalEdge> builder){

		HierarchicalGraphNodeBuilder<MockHierarchicalNode, MockHierarchicalEdge> nodeA = builder.findNode("A");
		HierarchicalGraphNodeBuilder<MockHierarchicalNode, MockHierarchicalEdge> nodeAA = builder.addNode("AA");
		
		nodeA.addSubNode(nodeAA);
	}
	
	@Test
	public void hierarchicalGraph00() throws Throwable {
		HierarchicalGraphBuilder<MockHierarchicalNode, MockHierarchicalEdge> builder = newBuilder();

		buildGraph00(builder);
		
		builder.setGraphName("hierarchicalGraph00");
		builder.graph().write(Ntro.graphWriter());
	}

	@Test
	public void hierarchicalGraph01() throws Throwable {
		HierarchicalGraphBuilder<MockHierarchicalNode, MockHierarchicalEdge> builder = newBuilder();

		buildGraph00(builder);
		buildGraph01(builder);
		
		builder.setGraphName("hierarchicalGraph01");
		builder.graph().write(Ntro.graphWriter());
	}


	@Test
	public void hierarchicalGraph10() throws Throwable {
		/*
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();

		HierarchicalGraphBuilder<HierarchicalGraphNode, HierarchicalGraphEdge, HierarchicalGraphSearchOptionsBuilder> builder = HierarchicalGraphBuilder.newBuilder("hierarchicalGraph01");

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

		HierarchicalGraph<HierarchicalGraphNode, HierarchicalGraphEdge, HierarchicalGraphSearchOptionsBuilder> graph = builder.toGraph();

		graph.write(Ntro.graphWriter());
		exceptionThrower.throwLastException();

		Ntro.asserter().assertTrue("nodeA has subNodes", nodeA.hasSubNodes());
		Ntro.asserter().assertFalse("nodeB does not have subNodes", nodeB.hasSubNodes());
		
		List<HierarchicalGraphNode> subNodes = new ArrayList<>();
		
		nodeA.forEachSubNode((walked, subNode) -> {
			subNodes.add(subNode);
		});
		
		exceptionThrower.throwLastException();
		
		Ntro.asserter().assertEquals(3, subNodes.size());

		List<HierarchicalGraphNode> allNodes = new ArrayList<>();
		
		graph.forEachNode(n -> {

			allNodes.add(n);
		});
		
		Ntro.asserter().assertEquals(6, allNodes.size());

		List<HierarchicalGraphNode> clusters = new ArrayList<>();
		
		graph.forEachNode(n -> {
			if(n.hasSubNodes() && !n.hasParent()) {
				clusters.add(n);
			}

			else if(n.hasSubNodes() && n.hasParent()) {
				clusters.add(n);
			}

			else if(!n.hasSubNodes() && n.hasParent()) {
				clusters.add(n);
			}
		});

		Ntro.asserter().assertEquals(2, clusters.size());
		*/
	}
}
