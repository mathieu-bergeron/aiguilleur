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

	private void buildGraph02(HierarchicalGraphBuilder<MockHierarchicalNode, MockHierarchicalEdge> builder){

		HierarchicalGraphNodeBuilder<MockHierarchicalNode, MockHierarchicalEdge> nodeAA = builder.findNode("AA");
		HierarchicalGraphNodeBuilder<MockHierarchicalNode, MockHierarchicalEdge> nodeAAA = builder.addNode("AAA");
		
		nodeAA.addSubNode(nodeAAA);
	}

	private void buildGraph03(HierarchicalGraphBuilder<MockHierarchicalNode, MockHierarchicalEdge> builder){

		HierarchicalGraphNodeBuilder<MockHierarchicalNode, MockHierarchicalEdge> nodeA = builder.findNode("A");
		HierarchicalGraphNodeBuilder<MockHierarchicalNode, MockHierarchicalEdge> nodeAA = builder.findNode("AA");

		HierarchicalGraphNodeBuilder<MockHierarchicalNode, MockHierarchicalEdge> node0 = builder.addNode("0");

		HierarchicalGraphNodeBuilder<MockHierarchicalNode, MockHierarchicalEdge> nodeF = builder.addNode("F");
		HierarchicalGraphNodeBuilder<MockHierarchicalNode, MockHierarchicalEdge> nodeFF = builder.addNode("FF");
		
		nodeF.addSubNode(nodeFF);
		
		builder.addEdge(nodeAA, "AA_FF", nodeFF);
		builder.addEdge(node0, "0A", nodeA);
		builder.addEdge(node0, "00", node0);
	}

	@Test
	public void subNodes() throws Throwable {
		HierarchicalGraphBuilder<MockHierarchicalNode, MockHierarchicalEdge> builder = newBuilder();

		HierarchicalGraphNodeBuilder<MockHierarchicalNode, MockHierarchicalEdge> nodeBuilderA = builder.addNode("A");
		HierarchicalGraphNodeBuilder<MockHierarchicalNode, MockHierarchicalEdge> nodeBuilderAA = builder.addNode("AA");
		HierarchicalGraphNodeBuilder<MockHierarchicalNode, MockHierarchicalEdge> nodeBuilderAAA = builder.addNode("AAA");
		
		nodeBuilderA.addSubNode(nodeBuilderAA);
		nodeBuilderAA.addSubNode(nodeBuilderAAA);
		
		Ntro.asserter().assertFalse("Not empty", nodeBuilderA.node().subNodes().isEmpty());
		Ntro.asserter().assertFalse("A has no parents", nodeBuilderA.node().hasParent());
		Ntro.asserter().assertTrue("A is a cluster", nodeBuilderA.node().hasSubNodes());
		Ntro.asserter().assertTrue("AA has a parent", nodeBuilderAA.node().hasParent());
		Ntro.asserter().assertTrue("AA is a cluster", nodeBuilderAA.node().hasSubNodes());


		Ntro.asserter().assertTrue("Contains AA", nodeBuilderA.node().subNodes().ifSome(visitedNode -> visitedNode.node() == nodeBuilderAA.node()));
		Ntro.asserter().assertTrue("Contains AAA", nodeBuilderA.node().subNodes().ifSome(visitedNode -> visitedNode.node() == nodeBuilderAAA.node()));

		
		builder.setGraphName("subNodes");
		builder.graph().write(Ntro.graphWriter());
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
	public void hierarchicalGraph02() throws Throwable {
		HierarchicalGraphBuilder<MockHierarchicalNode, MockHierarchicalEdge> builder = newBuilder();

		buildGraph00(builder);
		buildGraph01(builder);
		buildGraph02(builder);
		
		builder.setGraphName("hierarchicalGraph02");
		builder.graph().write(Ntro.graphWriter());
	}

	@Test
	public void hierarchicalGraph03() throws Throwable {
		HierarchicalGraphBuilder<MockHierarchicalNode, MockHierarchicalEdge> builder = newBuilder();

		buildGraph00(builder);
		buildGraph01(builder);
		buildGraph02(builder);
		buildGraph03(builder);
		
		builder.setGraphName("hierarchicalGraph03");
		builder.graph().write(Ntro.graphWriter());
	}

}
