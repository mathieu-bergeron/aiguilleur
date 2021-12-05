package ca.ntro.core.reflection;

import ca.ntro.core.graphs.generic_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriter;
import ca.ntro.core.reflection.object_graph.LocalHeap;
import ca.ntro.core.reflection.object_graph.ObjectGraphNtro;
import ca.ntro.core.reflection.object_graph.ObjectGraphSearchOptionsBuilder;
import ca.ntro.core.reflection.object_graph.ObjectNode;
import ca.ntro.core.reflection.object_graph.ReferenceEdge;
import ca.ntro.core.reflection.object_updates.ObjectUpdates;

public class ObjectGraphJdk extends ObjectGraphNtro {

	public ObjectGraphJdk(Object o) {
		super(o);
	}

	protected LocalHeap createLocalHeap() {
		return new LocalHeapJdk();
	}

	@Override
	protected InternalGraphWriter<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> internalGraphWriter() {
		throw new RuntimeException("TODO");
	}

	@Override
	protected GenericGraphStructure<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> graphStructure() {
		// TODO Auto-generated method stub
		return null;
	}


}
