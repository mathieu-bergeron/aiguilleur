package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.directed_graph.DirectedNode;
import ca.ntro.core.reflection.object_updates.ObjectUpdates;

public interface ObjectNode extends DirectedNode<ObjectNode, ReferenceEdge> {
	
	Object object();

	// TODO: describe an object of the graph
	//       as a sequence of UPDATE/INSERT/DELETE operations
	ObjectUpdates asUpdates();

}
