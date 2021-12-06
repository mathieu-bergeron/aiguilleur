package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generics.generic_graph.Node;
import ca.ntro.core.reflection.object_updates.ObjectUpdates;

public interface ObjectNode extends Node<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> {
	
	Object object();

	// TODO: describe an object of the graph
	//       as a sequence of UPDATE/INSERT/DELETE operations
	ObjectUpdates asUpdates();

}
