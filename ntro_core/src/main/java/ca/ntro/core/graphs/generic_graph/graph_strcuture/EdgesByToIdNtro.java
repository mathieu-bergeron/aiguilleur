package ca.ntro.core.graphs.generic_graph.graph_strcuture;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeName;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.generic_graph.EdgeNameReducer;
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
	public <R> void _reduceEdgeNames(ResultNtro<R> result, EdgeNameReducer<R> reducer) {
		if(result.hasException()) {
			return;
		}

		for(E edge: edgesMap.values()) {

			try {
				
				result.registerValue(reducer.reduceStep(result.value(), edge.name()));

			}catch(Throwable t) {
				
				result.registerException(t);
				return;
				
			}
		}
	}

	@Override
	public <R> void _reduceEdgesByName(EdgeName edgeName, ResultNtro<R> result, EdgeReducer<N,E,SO,R> reducer) {
		if(result.hasException()) {
			return;
		}

		for(E edge: edgesMap.values()) {
			try {
				
				result.registerValue(reducer.reduceEdge(result.value(), edge));

			}catch(Throwable t) {
				
				result.registerException(t);
				return;
			}
		}
	}
}
