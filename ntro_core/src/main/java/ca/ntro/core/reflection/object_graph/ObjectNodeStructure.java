package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericNodeStructure;

public interface ObjectNodeStructure 
	   extends   GenericNodeStructure<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> {
	
	ObjectNode asNode();
	ObjectGraph parentGraph();

}
