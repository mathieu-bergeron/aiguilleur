package ca.ntro.core.reflection;

import ca.ntro.core.graphs.directed_graph.DirectedSearchOptions;
import ca.ntro.core.graphs.directed_graph.InternalDirectedGraphWriterNtro;
import ca.ntro.core.graphs.generics.graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.graph.InternalGraphWriter;
import ca.ntro.core.graphs.generics.graph.InternalGraphWriterNtro;
import ca.ntro.core.reflection.object_graph.LocalHeap;
import ca.ntro.core.reflection.object_graph.ObjectGraphNtro;
import ca.ntro.core.reflection.object_graph.ObjectGraphStructureNtro;
import ca.ntro.core.reflection.object_graph.ObjectNode;
import ca.ntro.core.reflection.object_graph.ReferenceEdge;

public class ObjectGraphJdk extends ObjectGraphNtro {
	

	public ObjectGraphJdk(Object o) {
		super(o);
	}

	@Override
	protected ObjectGraphStructureNtro newObjectGraphStructureInstance() {
		return new ObjectGraphStructureJdk();
	}

	protected LocalHeap createLocalHeap() {
		return new LocalHeapJdk();
	}


	@Override
	protected InternalGraphWriter<ObjectNode, ReferenceEdge, DirectedSearchOptions> newInternalGraphWriterInstance() {
		return new InternalDirectedGraphWriterNtro<ObjectNode, ReferenceEdge>();
	}

}
