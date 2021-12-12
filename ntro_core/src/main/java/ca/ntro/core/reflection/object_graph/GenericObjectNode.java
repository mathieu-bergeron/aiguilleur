package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.reflection.object_updates.ObjectUpdate;

public interface GenericObjectNode {

	Class<?> type();
	Object object();

	// TODO: describe an object of the graph
	//       as a sequence of UPDATE/INSERT/DELETE operations
	ObjectUpdate asUpdates();

}
