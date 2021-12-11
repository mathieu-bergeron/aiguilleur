package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedNodeStructure;

public interface ObjectNodeStructure 
	   extends   GenericDirectedNodeStructure<ObjectNode, 
	                                          ReferenceEdge, 
	                                          ObjectGraphSearchOptions> {
	
	ObjectNode node();
	ObjectGraph parentGraph();

}
