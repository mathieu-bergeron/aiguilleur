package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generics.directed_graph.Direction;
import ca.ntro.core.graphs.generics.directed_graph.EdgeNtro;
import ca.ntro.core.graphs.generics.directed_graph.EdgeType;
import ca.ntro.core.graphs.generics.directed_graph.EdgeTypeNtro;

public class ReferenceEdgeNtro 
       
       extends EdgeNtro<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder>

	   implements ReferenceEdge {

	public ReferenceEdgeNtro(ObjectNode from, EdgeType edgeName, ObjectNode to) {
		super(from, edgeName, to);
	}

	public ReferenceEdgeNtro(ObjectNode from, String attributeName, ObjectNode to) {
		super(from, new EdgeTypeNtro(Direction.FORWARD, attributeName) ,to);
	}

}
