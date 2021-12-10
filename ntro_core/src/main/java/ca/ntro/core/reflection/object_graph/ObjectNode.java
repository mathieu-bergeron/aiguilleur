package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.directed_graph.DirectedNode;
import ca.ntro.core.reflection.object_updates.ObjectUpdates;

public interface ObjectNode extends DirectedNode<ObjectNode, ReferenceEdge> {

	Class<?> type();

	Object object();
	<V> V object(Class<V> _class);

	boolean isList();
	boolean isMap();
	boolean isUserDefinedObject();
	boolean isSimpleValue();
	
	ObjectNodeList              asList();
	ObjectNodeMap               asMap();
	ObjectNodeUserDefinedObject asUserDefinedObject();
	ObjectNodeSimpleValue       asSimpleValue();

	// TODO: describe an object of the graph
	//       as a sequence of UPDATE/INSERT/DELETE operations
	ObjectUpdates asUpdates();

}
