package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphNtro;
import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.graphs.generics.graph.GenericInternalGraphWriter;
import ca.ntro.core.reflection.object_updates.ObjectUpdates;

public abstract class ObjectGraphNtro 

       extends        GenericDirectedGraphNtro<ObjectNode, 
                                               ReferenceEdge,
                                               ObjectGraphSearchOptions,
                                               ObjectGraphWriterOptions> 

       implements     ObjectGraph {

	public ObjectGraphNtro(Object o) {
		super();

		ObjectGraphStructureNtro graphStructure = newObjectGraphStructureInstance();
		graphStructure.setStartObjects(new Object[] {o});
		graphStructure.setGraph(this);
		
		setId(GraphId.fromGraphName(o.getClass().getSimpleName()));

		setGraphStructure(graphStructure);
	}

	protected abstract ObjectGraphStructureNtro newObjectGraphStructureInstance();

	@Override
	public ObjectUpdates objectAsUpdates(Object object) {
		// TODO: describe an object of the graph
		//       as a sequence of UPDATE/INSERT/DELETE operations
		return null;
	}

	@Override
	protected GenericInternalGraphWriter<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions, ObjectGraphWriterOptions> newInternalGraphWriterInstance() {
		return new InternalObjectGraphWriterNtro();
	}

	@Override
	protected ObjectGraphSearchOptions newDefaultSearchOptionsInstance() {
		return new ObjectGraphSearchOptionsNtro();
	}

	@Override
	protected ObjectGraphWriterOptions newDefaultGraphWriterOptionsInstance() {
		return new ObjectGraphWriterOptionsNtro();
	}

}
