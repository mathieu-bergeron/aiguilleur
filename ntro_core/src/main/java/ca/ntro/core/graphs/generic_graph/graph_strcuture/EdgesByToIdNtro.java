package ca.ntro.core.graphs.generic_graph.graph_strcuture;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeName;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.generic_graph.EdgeNameReducer;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class EdgesByToIdNtro<N extends Node<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptions> 

       implements EdgesByToId<N,E,SO> {
	

	private Map<String, E> edgesMap = new HashMap<>();

	public Map<String, E> getEdgesMap() {
		return edgesMap;
	}

	public void setEdgesMap(Map<String, E> edgesMap) {
		this.edgesMap = edgesMap;
	}

	@Override
	public void addEdge(E edge) {
		getEdgesMap().put(edge.to().id().toKey().toString(), edge);
	}

	@Override
	public <R> Result<R> reduceEdgeNames(R initialValue, EdgeNameReducer<R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		for(E edge: edgesMap.values()) {
			if(result.hasException()) {
				break;
			}
			
			try {
				
				result.registerValue(reducer.reduceStep(result.value(), edge.id()));

			}catch(Throwable t) {
				
				result.registerException(t);
				
			}
		}
		
		return result;
	}

	@Override
	public <R> Result<R> reduceEdgesByName(EdgeName edgeName, R initialValue, EdgeReducer<N,E,SO,R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		for(E edge: edgesMap.values()) {
			if(result.hasException()) {
				break;
			}
			
			try {
				
				result.registerValue(reducer.reduceEdge(initialValue, edge));

			}catch(Throwable t) {
				
				result.registerException(t);
				
			}
		}
		
		return result;
	}
}
