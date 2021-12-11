package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.reflection.object_updates.ObjectUpdates;

public interface ObjectGraph 
      
       extends   GenericDirectedGraph<ObjectNode, 
                                      ReferenceEdge, 
                                      ObjectGraphSearchOptions,
                                      ObjectGraphWriterOptions> {
	

	ObjectUpdates objectAsUpdates(Object object);

}
