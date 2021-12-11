package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericInternalDirectedGraphWriter;

public interface InternalObjectGraphWriter       

       extends   GenericInternalDirectedGraphWriter<ObjectNode, 
                                             ReferenceEdge, 
                                             ObjectGraphSearchOptions,
                                             ObjectGraphWriterOptions> {

}
