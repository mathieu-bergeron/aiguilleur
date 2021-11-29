package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.EdgeName;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.generic_graph.EdgeNameReducer;
import ca.ntro.core.graphs.generic_graph.NodeNtro;
import ca.ntro.core.wrappers.result.Result;

public class   ObjectNodeNtro 

	   extends NodeNtro<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions>

	   implements ObjectNode {
	
	
	public ObjectNodeNtro(NodeId nodeId) {
		super(nodeId);
	}

	@Override
	protected <R> Result<R> reduceEdgeNames(R initialValue, EdgeNameReducer<R> reducer) {

		return null;
	}

	@Override
	protected <R> Result<R> reduceEdgesByName(EdgeName edgeName, 
			                                  R initialValue, 
			                                  EdgeReducer<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions, R> reducer) {
		return null;
	}
}
