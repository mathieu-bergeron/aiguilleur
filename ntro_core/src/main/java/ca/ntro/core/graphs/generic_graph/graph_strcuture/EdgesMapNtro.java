package ca.ntro.core.graphs.generic_graph.graph_strcuture;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeName;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.StepReducer;
import ca.ntro.core.graphs.generic_graph.EdgeNameReducer;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class EdgesMapNtro<N extends Node<N,E,SO>, 
                                   E extends Edge<N,E,SO>,
                                   SO extends SearchOptions,
                                   SUBMAP extends EdgesMap<N,E,SO>>

	   implements     EdgesMap<N,E,SO> {
	
	private Map<String, SUBMAP> edgesMap = new HashMap<>();

	public Map<String, SUBMAP> getEdgesMap() {
		return edgesMap;
	}

	public void setEdgesMap(Map<String, SUBMAP> edgesMap) {
		this.edgesMap = edgesMap;
	}

	protected abstract SUBMAP createSubMap();

	protected abstract String getSubMapKey(E edge);
	protected abstract String getSubMapKey(EdgeName edgeName);


	@Override
	public void addEdge(E edge) {
		SUBMAP subMap = getEdgesMap().get(getSubMapKey(edge));

		if(subMap == null) {
			subMap = createSubMap();
			getEdgesMap().put(getSubMapKey(edge), subMap);
		}

		subMap.addEdge(edge);
	}

	@Override
	public <R> Result<R> reduceEdgeNames(R initialValue, EdgeNameReducer<R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		for(SUBMAP subMap: edgesMap.values()) {
			if(result.hasException()) {
				break;
			}

			Result<R> nextResult = subMap.reduceEdgeNames(result.value(), reducer);
			
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
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		SUBMAP subMap = getEdgesMap().get(getSubMapKey(edgeName));
		
		if(subMap != null) {

			Result<R> nextResult = subMap.reduceEdgesByName(edgeName, result.value(), reducer);
			
			if(nextResult.hasException()) {
				
				result.registerException(nextResult.exception());
				
			}else {
				
				result.registerValue(nextResult.value());
			}
		}

		return result;
	}
}
