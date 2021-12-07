package ca.ntro.core.reflection;

import ca.ntro.core.graphs.generics.generic_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.generic_graph.InternalGraphWriter;
import ca.ntro.core.graphs.generics.generic_graph.InternalGraphWriterNtro;
import ca.ntro.core.reflection.object_graph.LocalHeap;
import ca.ntro.core.reflection.object_graph.ObjectGraphNtro;
import ca.ntro.core.reflection.object_graph.ObjectGraphSearchOptionsBuilder;
import ca.ntro.core.reflection.object_graph.ObjectNode;
import ca.ntro.core.reflection.object_graph.ReferenceEdge;

public class ObjectGraphJdk extends ObjectGraphNtro {
	
	private ObjectGraphStructureJdk graphStructure;

	public ObjectGraphJdk(Object o) {
		super(o);
		
		this.graphStructure = new ObjectGraphStructureJdk(o, this);
	}

	protected LocalHeap createLocalHeap() {
		return new LocalHeapJdk();
	}

	@Override
	public GenericGraphStructure<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> graphStructure() {
		return graphStructure;
	}

	@Override
	protected InternalGraphWriter<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> newInternalGraphWriterInstance() {
		return new InternalGraphWriterNtro<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder>();
	}
}
