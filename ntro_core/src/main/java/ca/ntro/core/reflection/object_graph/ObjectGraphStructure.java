package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphStructure;

public interface ObjectGraphStructure 
       extends   GenericDirectedGraphStructure<ObjectNode, 
                                               ReferenceEdge,
                                               ObjectGraphSearchOptions> {

	String label();

}
