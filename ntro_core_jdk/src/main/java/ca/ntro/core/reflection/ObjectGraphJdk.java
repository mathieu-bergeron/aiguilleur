package ca.ntro.core.reflection;

import ca.ntro.core.reflection.object_graph.LocalHeap;
import ca.ntro.core.reflection.object_graph.ObjectGraphNtro;
import ca.ntro.core.reflection.object_graph.ObjectGraphStructureNtro;

public class ObjectGraphJdk extends ObjectGraphNtro {
	

	public ObjectGraphJdk(Object o) {
		super(o);
	}

	public ObjectGraphJdk(Object o, String graphName) {
		super(o, graphName);
	}

	@Override
	protected ObjectGraphStructureNtro newObjectGraphStructureInstance() {
		return new ObjectGraphStructureJdk();
	}

	protected LocalHeap createLocalHeap() {
		return new LocalHeapJdk();
	}

}
