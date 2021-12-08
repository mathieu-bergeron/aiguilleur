package ca.ntro.core.reflection;

import ca.ntro.core.graphs.directed_graph.DirectedSearchOptions;
import ca.ntro.core.graphs.directed_graph.InternalDirectedGraphWriterNtro;
import ca.ntro.core.graphs.generics.graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.graph.InternalGraphWriter;
import ca.ntro.core.graphs.generics.graph.InternalGraphWriterNtro;
import ca.ntro.core.reflection.object_graph.LocalHeap;
import ca.ntro.core.reflection.object_graph.ObjectGraphNtro;
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
	public GenericGraphStructure<ObjectNode, ReferenceEdge, DirectedSearchOptions> graphStructure() {
		return graphStructure;
	}

	@Override
	protected InternalGraphWriter<ObjectNode, ReferenceEdge, DirectedSearchOptions> newInternalGraphWriterInstance() {
		return new InternalDirectedGraphWriterNtro<ObjectNode, ReferenceEdge>();
	}
}
