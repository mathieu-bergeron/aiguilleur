package ca.ntro.core.reflection;

import ca.ntro.core.reflection.object_graph.LocalHeap;
import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.reflection.object_graph.ObjectGraphStructureNtro;

public class ObjectGraphStructureJdk extends ObjectGraphStructureNtro {

	public ObjectGraphStructureJdk(Object o, ObjectGraph graph) {
		super(o, graph);
	}

	@Override
	protected LocalHeap createLocalHeap() {
		return new LocalHeapJdk();
	}

}
