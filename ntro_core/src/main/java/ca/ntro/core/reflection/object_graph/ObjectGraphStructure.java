package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.directed_graph.DirectedGraphStructure;

public interface ObjectGraphStructure 
       extends   DirectedGraphStructure<ObjectNode, ReferenceEdge> {

	String label();

}
