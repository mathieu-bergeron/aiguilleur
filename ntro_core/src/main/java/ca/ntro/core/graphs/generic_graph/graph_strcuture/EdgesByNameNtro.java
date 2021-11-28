package ca.ntro.core.graphs.generic_graph.graph_strcuture;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeName;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.StepReducer;
import ca.ntro.core.graphs.generic_graph.EdgeNameReducer;
import ca.ntro.core.identifyers.Name;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class      EdgesByNameNtro <N extends Node<N,E,SO>, 
                                     E extends Edge<N,E,SO>,
                                     SO extends SearchOptions> 

       implements EdgesByName<N,E,SO> {
	
	private Map<String, EdgesByToId<N,E,SO>> edgesByName = new HashMap<>();

	public Map<String, EdgesByToId<N, E, SO>> getEdgesByName() {
		return edgesByName;
	}

	public void setEdgesByName(Map<String, EdgesByToId<N, E, SO>> edgesByName) {
		this.edgesByName = edgesByName;
	}

	@Override
	public void addEdge(E edge) {
		EdgesByToId<N,E,SO> edgesByToId = getEdgesByName().get(edge.id().name().toString());
		
		if(edgesByToId == null) {
			edgesByToId = new EdgesByToIdNtro<>();
			getEdgesByName().put(edge.id().name().toString(), edgesByToId);
		}
		
		edgesByToId.addEdge(edge);
	}

	@Override
	public <R> Result<R> reduceEdgeNames(Direction direction, R initialValue, EdgeNameReducer<R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		for(String edgeName : edgesByName.keySet()) {
			if(result.hasException()) {
				break;
			}

			ResultNtro<R> nextResult = reducer.reduceStep(result.value(), new EdgeNameNtro(direction, new Name(edgeName)));
			
			if(nextResult.hasException()) {
				
				result.registerException(nextResult.exception());
				
			}else {
				
				result.registerValue(nextResult.value());
			}
		}
		
		return result;
	}

	@Override
	public <R> Result<R> reduceEdgesByName(EdgeName edgeName, R initialValue, StepReducer<N, E, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

}
