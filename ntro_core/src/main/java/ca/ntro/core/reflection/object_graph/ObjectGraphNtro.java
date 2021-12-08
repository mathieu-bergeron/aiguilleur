package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.directed_graph.DirectedGraphNtro;
import ca.ntro.core.reflection.object_updates.ObjectUpdates;

public abstract class ObjectGraphNtro 

       extends        DirectedGraphNtro<ObjectNode, ReferenceEdge> 

       implements     ObjectGraph {
	
	@Override
	public ObjectUpdates objectAsUpdates(Object object) {
		// TODO: describe an object of the graph
		//       as a sequence of UPDATE/INSERT/DELETE operations
		return null;
	}

}
