package ca.ntro.core.reflection;

import ca.ntro.core.graphs.generic_graph.InternalGraphWriter;
import ca.ntro.core.reflection.object_graph.LocalHeap;
import ca.ntro.core.reflection.object_graph.ObjectGraphNtro;
import ca.ntro.core.reflection.object_graph.ObjectGraphSearchOptions;
import ca.ntro.core.reflection.object_graph.ObjectNode;
import ca.ntro.core.reflection.object_graph.ReferenceEdge;

public class ObjectGraphJdk extends ObjectGraphNtro {

	public ObjectGraphJdk(Object startObject) {
		super(startObject);
	}

	public ObjectGraphJdk(Object[] startObjects) {
		super(startObjects);
	}

	@Override
	protected LocalHeap createLocalHeap() {
		return new LocalHeapJdk();
	}

	@Override
	protected InternalGraphWriter<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions> internalGraphWriter() {
		throw new RuntimeException("TODO");
	}

}
