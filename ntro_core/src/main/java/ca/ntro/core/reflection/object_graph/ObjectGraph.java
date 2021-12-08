package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.directed_graph.DirectedGraph;
import ca.ntro.core.reflection.object_updates.ObjectUpdates;

public interface ObjectGraph 
      
       extends DirectedGraph<ObjectNode, ReferenceEdge> {
	

	ObjectUpdates objectAsUpdates(Object object);

}
