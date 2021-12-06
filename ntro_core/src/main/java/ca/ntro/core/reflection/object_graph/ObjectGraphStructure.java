package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generics.generic_graph.GenericGraphStructure;

public interface ObjectGraphStructure 
       extends   GenericGraphStructure<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder> {

	String label();

}
