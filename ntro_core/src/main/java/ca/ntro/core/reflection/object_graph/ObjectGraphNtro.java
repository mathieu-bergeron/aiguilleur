package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.generic_graph.GenericGraphNtro;

public abstract class ObjectGraphNtro 

       extends GenericGraphNtro<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> 

       implements ObjectGraph {

	public ObjectGraphNtro(Object o) {
	}

	@Override
	public GraphId id() {
		return GraphId.fromGraphName(label());
	}

	@Override
	public String label() {
		return ((ObjectGraphStructure) graphStructure()).label();
	}

	@Override
	public ObjectGraphSearchOptionsBuilder defaultSearchOptions() {
		return new ObjectGraphSearchOptionsBuilderNtro();
	}

}
