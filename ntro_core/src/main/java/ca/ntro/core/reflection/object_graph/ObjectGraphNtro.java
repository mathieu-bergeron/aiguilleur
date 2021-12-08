package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.directed_graph.DirectedGraphNtro;
import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.reflection.object_updates.ObjectUpdates;

public abstract class ObjectGraphNtro 

       extends        DirectedGraphNtro<ObjectNode, ReferenceEdge> 

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

}
