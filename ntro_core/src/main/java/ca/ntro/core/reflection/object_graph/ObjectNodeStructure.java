package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.directed_graph.DirectedNodeStructure;

public interface ObjectNodeStructure 
	   extends   DirectedNodeStructure<ObjectNode, ReferenceEdge> {
	
	ObjectNode asNode();
	ObjectGraph parentGraph();

}
