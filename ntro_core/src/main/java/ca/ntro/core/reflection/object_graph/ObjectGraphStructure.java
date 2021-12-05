package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generic_graph.GenericGraphStructure;

public interface ObjectGraphStructure 
       extends   GenericGraphStructure<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> {

	String label();

}
