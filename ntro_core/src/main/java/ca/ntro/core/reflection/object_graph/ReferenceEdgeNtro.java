package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.EdgeTypeNtro;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;

public class ReferenceEdgeNtro 
       
       extends EdgeNtro<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions>

	   implements ReferenceEdge {

	public ReferenceEdgeNtro(ObjectNode from, EdgeType edgeName, ObjectNode to) {
		super(from, edgeName, to);
	}

	public ReferenceEdgeNtro(ObjectNode from, String attributeName, ObjectNode to) {
		super(from, new EdgeTypeNtro(Direction.FORWARD, attributeName) ,to);
	}

}
