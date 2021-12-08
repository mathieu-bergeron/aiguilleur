package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generics.graph.GenericNodeStructure;

public interface ObjectNodeStructure 
	   extends   GenericNodeStructure<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> {
	
	ObjectNode asNode();
	ObjectGraph parentGraph();

}
