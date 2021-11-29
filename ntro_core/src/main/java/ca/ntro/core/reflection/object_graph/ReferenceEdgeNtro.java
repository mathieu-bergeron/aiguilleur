package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.EdgeName;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;

public class ReferenceEdgeNtro 
       
       extends EdgeNtro<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions>

	   implements ReferenceEdge {

	public ReferenceEdgeNtro(ObjectNode from, EdgeName edgeName, ObjectNode to) {
		super(from, edgeName, to);
	}

}
