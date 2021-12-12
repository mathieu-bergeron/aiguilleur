package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedEdgeNtro;

public class ReferenceEdgeNtro 
       
       extends GenericDirectedEdgeNtro<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions>

	   implements ReferenceEdge {

	public ReferenceEdgeNtro(ObjectNode fromNode, String edgeName, ObjectNode toNode) {
		super(fromNode, edgeName, toNode);
	}


}
