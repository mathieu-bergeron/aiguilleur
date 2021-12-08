package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.common.EdgeTypeNtro;
import ca.ntro.core.graphs.generics.graph.GenericEdgeNtro;

public class ReferenceEdgeNtro 
       
       extends GenericEdgeNtro<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder>

	   implements ReferenceEdge {

	public ReferenceEdgeNtro(ObjectNode from, EdgeType edgeName, ObjectNode to) {
		super(from, edgeName, to);
	}

	public ReferenceEdgeNtro(ObjectNode from, String attributeName, ObjectNode to) {
		super(from, new EdgeTypeNtro(Direction.FORWARD, attributeName) ,to);
	}

}
