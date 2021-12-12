package ca.ntro.core.reflection;

import ca.ntro.core.reflection.object_graph.LocalHeap;
import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.reflection.object_graph.ObjectGraphStructureNtro;
import ca.ntro.core.reflection.object_graph.ObjectNode;
import ca.ntro.core.stream.Stream;

public class ObjectGraphStructureJdk extends ObjectGraphStructureNtro {

	public ObjectGraphStructureJdk() {
		super();
	}

	public ObjectGraphStructureJdk(Object o, ObjectGraph graph) {
		super(o, graph);
	}

	@Override
	protected LocalHeap newLocalHeapInstance() {
		return new LocalHeapJdk();
	}


}
