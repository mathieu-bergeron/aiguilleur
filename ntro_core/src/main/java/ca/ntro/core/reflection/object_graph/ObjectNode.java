package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.Node;

public interface ObjectNode extends Node<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions> {
	
	Object object();

}
