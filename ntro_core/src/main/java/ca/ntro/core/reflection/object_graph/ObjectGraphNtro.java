package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphNtro;
import ca.ntro.core.graphs.generics.directed_graph.GraphId;
import ca.ntro.core.reflection.object_updates.ObjectUpdates;

public abstract class ObjectGraphNtro 

       extends GenericDirectedGraphNtro<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> 

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

	@Override
	public ObjectUpdates objectAsUpdates(Object object) {
		// TODO: describe an object of the graph
		//       as a sequence of UPDATE/INSERT/DELETE operations
		return null;
	}

}
