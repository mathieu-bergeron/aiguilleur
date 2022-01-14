package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphNtro;
import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.graphs.generics.graph.GenericInternalGraphWriter;
import ca.ntro.core.stream.Stream;

public abstract class ObjectGraphNtro 

       extends        GenericDirectedGraphNtro<ObjectNode, 
                                               ReferenceEdge,
                                               ObjectGraphSearchOptions,
                                               ObjectGraphWriterOptions> 

       implements     ObjectGraph {

	public ObjectGraphNtro(Object o) {
		super();

		initialize(o, o.getClass().getSimpleName());
	}

	public ObjectGraphNtro(Object o, String graphName) {
		super();

		initialize(o, graphName);
	}

	private void initialize(Object o, String graphName) {

		ObjectGraphStructureNtro graphStructure = newObjectGraphStructureInstance();
		graphStructure.setStartObjects(new Object[] {o});
		graphStructure.setGraph(this);
		
		setId(GraphId.fromGraphName(graphName));

		setGraphStructure(graphStructure);
	}

	protected abstract ObjectGraphStructureNtro newObjectGraphStructureInstance();


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
