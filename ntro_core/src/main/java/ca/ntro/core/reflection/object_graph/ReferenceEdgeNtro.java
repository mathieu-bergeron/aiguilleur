package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.directed_graph.DirectedEdgeNtro;

public class ReferenceEdgeNtro 
       
       extends DirectedEdgeNtro<ObjectNode, ReferenceEdge>

	   implements ReferenceEdge {

	public ReferenceEdgeNtro(ObjectNode fromNode, String edgeName, ObjectNode toNode) {
		super(fromNode, edgeName, toNode);
	}


}
