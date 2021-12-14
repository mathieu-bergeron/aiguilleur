package ca.ntro.core.reflection.object_graph;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.mock.linked_list.LinkedList;
import ca.ntro.core.reflection.mock.linked_list.MockLinkedList;
import ca.ntro.core.reflection.mock.objects.TestObject01;
import ca.ntro.core.reflection.mock.objects.TestObject02;
import ca.ntro.core.reflection.mock.objects.TestObjectCycle;
import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.tests.NtroTests;

public class ObjectGraphTests extends NtroTests {

	@Test
	public void testObjectGraphLinkedList01() throws Throwable {
		
		LinkedList<String> linkedList = new MockLinkedList<>();

		ObjectGraph graph = Ntro.reflectionService().objectGraph(linkedList, "linkedList00");
		graph.write(Ntro.graphWriter());
		
		linkedList.add("i01");

		graph = Ntro.reflectionService().objectGraph(linkedList, "linkedList01");
		graph.write(Ntro.graphWriter());
		
		linkedList.add("i02");

		graph = Ntro.reflectionService().objectGraph(linkedList, "linkedList02");
		graph.write(Ntro.graphWriter());

		linkedList.add("i03");

		graph = Ntro.reflectionService().objectGraph(linkedList, "linkedList03");
		graph.write(Ntro.graphWriter());
		
	}


	@Test
	public void testObjectGraph01() throws Throwable {
		
		TestObject01 o = new TestObject01();
		
		ObjectGraph graph = Ntro.reflectionService().objectGraph(o);
		graph.write(Ntro.graphWriter());
		
		List<Object> rootValues = new ArrayList<>();

		graph.startNodes().forEach(n -> {
			rootValues.add(n.object());
		});
		
		Ntro.asserter().assertTrue("Should contain " + o, rootValues.contains(o));
		Ntro.asserter().assertEquals(1, rootValues.size());
		
		List<Object> subValues = new ArrayList<>();

		graph.nodes().forEach(n -> {
			subValues.add(n.object());
		});
		
		Ntro.asserter().assertTrue("Should contain " + o, subValues.contains(o));
		Ntro.asserter().assertTrue("Should contain " + o.getAttr01(), subValues.contains(o.getAttr01()));
		//Ntro.asserter().assertEquals(2, subValues.size());
	}

	@Test
	public void testObjectGraph02() throws Throwable {
		TestObject02 o = new TestObject02();
		
		ObjectGraph graph = Ntro.reflectionService().objectGraph(o);
		graph.write(Ntro.graphWriter());
		
		List<Object> subValues = new ArrayList<>();

		graph.nodes().forEach(n -> {
			subValues.add(n.object());
		});

		
		//Ntro.asserter().assertEquals(3, subValues.size());
		Ntro.asserter().assertTrue("Should contain", subValues.contains(o));
		Ntro.asserter().assertTrue("Should contain", subValues.contains(o.getTestObject01()));
		Ntro.asserter().assertTrue("Should contain", subValues.contains(o.getTestObject01().getAttr01()));
	}

	@Test
	public void testObjectCycle() throws Throwable {
		TestObjectCycle o = new TestObjectCycle();
		
		ObjectGraph graph = Ntro.reflectionService().objectGraph(o);
		graph.write(Ntro.graphWriter());
		
		List<Object> subValues = new ArrayList<>();

		graph.nodes().forEach(n -> {
			subValues.add(n.object());
		});
		
		Ntro.asserter().assertEquals(1, subValues.size());
		Ntro.asserter().assertTrue("Should contain", subValues.contains(o));

		/*
		List<DirectedEdgeTriple<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder>> edges = new ArrayList<>();
		graph.forEachEdge(edge -> {
			edges.add(new DirectedEdgeTriple<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder>(edge.from(), edge.edgeType, edge.to())));
		});*/
		

		//Ntro.asserter().assertEquals(1, edges.size());
	}

}
