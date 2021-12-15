package ca.ntro.core.reflection.object_graph;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.mock.linked_list.LinkedList;
import ca.ntro.core.reflection.mock.linked_list.MockLinkedList;
import ca.ntro.core.reflection.mock.liste_array.ListeArray;
import ca.ntro.core.reflection.mock.liste_gauche_droite.ListeGaucheDroite;
import ca.ntro.core.reflection.mock.objects.TestObject01;
import ca.ntro.core.reflection.mock.objects.TestObject02;
import ca.ntro.core.reflection.mock.objects.TestObjectCycle;
import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.tests.NtroTests;

public class ObjectGraphTests extends NtroTests {

	@Test
	public void leftRightList() throws Throwable {
		
		ListeGaucheDroite<String> list = new ListeGaucheDroite<>();

		ObjectGraph graph = Ntro.reflectionService().objectGraph(list, "leftRightList00");
		graph.write(Ntro.graphWriter());
		
		list.insert(0,"3");
		
		graph = Ntro.reflectionService().objectGraph(list, "leftRightList01");
		graph.write(Ntro.graphWriter());

		list.insert(0,"2");

		graph = Ntro.reflectionService().objectGraph(list, "leftRightList02");
		graph.write(Ntro.graphWriter());


		list.insert(0,"1");

		graph = Ntro.reflectionService().objectGraph(list, "leftRightList03");
		graph.write(Ntro.graphWriter());

		list.insert(0,"0");
		
		graph = Ntro.reflectionService().objectGraph(list, "leftRightList04");
		graph.write(Ntro.graphWriter());

		list.add("4");

		graph = Ntro.reflectionService().objectGraph(list, "leftRightList05");
		graph.write(Ntro.graphWriter());
		
		list.add("5");

		graph = Ntro.reflectionService().objectGraph(list, "leftRightList06");
		graph.write(Ntro.graphWriter());

		list.add("6");

		graph = Ntro.reflectionService().objectGraph(list, "leftRightList07");
		graph.write(Ntro.graphWriter());

		list.add("7");

		graph = Ntro.reflectionService().objectGraph(list, "leftRightList08");
		graph.write(Ntro.graphWriter());

		list.add("8");

		graph = Ntro.reflectionService().objectGraph(list, "leftRightList09");
		graph.write(Ntro.graphWriter());
	}

	@Test
	public void testObjectGraphLinkedList01() throws Throwable {
		
		LinkedList<String> list = new MockLinkedList<>();

		ObjectGraph graph = Ntro.reflectionService().objectGraph(list, "linkedList00");
		graph.write(Ntro.graphWriter());
		
		list.add("1");

		graph = Ntro.reflectionService().objectGraph(list, "linkedList01");
		graph.write(Ntro.graphWriter());
		
		list.add("2");

		graph = Ntro.reflectionService().objectGraph(list, "linkedList02");
		graph.write(Ntro.graphWriter());

		list.add("3");

		graph = Ntro.reflectionService().objectGraph(list, "linkedList03");
		graph.write(Ntro.graphWriter());
		
	}

	@Test
	public void testObjectGraphArrayList() throws Throwable {
		
		ListeArray<String> list = new ListeArray<String>(size -> {
			return new String[size];
		});

		ObjectGraph graph = Ntro.reflectionService().objectGraph(list, "arrayList00");
		graph.write(Ntro.graphWriter());
		
		list.add("2");

		graph = Ntro.reflectionService().objectGraph(list, "arrayList01");
		graph.write(Ntro.graphWriter());
		
		list.add("3");

		graph = Ntro.reflectionService().objectGraph(list, "arrayList02");
		graph.write(Ntro.graphWriter());

		list.insert(0,"1");

		graph = Ntro.reflectionService().objectGraph(list, "arrayList03");
		graph.write(Ntro.graphWriter());

		list.insert(0,"0");


		graph = Ntro.reflectionService().objectGraph(list, "arrayList04");
		graph.write(Ntro.graphWriter());

		list.add("4");


		graph = Ntro.reflectionService().objectGraph(list, "arrayList05");
		graph.write(Ntro.graphWriter());

		list.add("5");

		graph = Ntro.reflectionService().objectGraph(list, "arrayList06");
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
