package ca.ntro.core.reflection;

import ca.ntro.core.graphs.generics.directed_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.directed_graph.InternalGraphWriter;
import ca.ntro.core.graphs.generics.directed_graph.InternalGraphWriterNtro;
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
	public InternalGraphWriter<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> internalGraphWriter() {
		return new InternalGraphWriterNtro<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder>();
	}

	@Override
	public GenericGraphStructure<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> graphStructure() {
		return graphStructure;
	}

}
