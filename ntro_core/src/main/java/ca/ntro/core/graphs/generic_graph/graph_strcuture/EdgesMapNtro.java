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
	public <R> void _reduceEdgeNames(ResultNtro<R> result, EdgeNameReducer<R> reducer) {
		if(result.hasException()) {
			return;
		}
		
		for(SUBMAP subMap: edgesMap.values()) {
			if(result.hasException()) {
				break;
			}

			subMap._reduceEdgeNames(result, reducer);
		}
	}

	@Override
	public <R> void _reduceEdgesByName(EdgeName edgeName, ResultNtro<R> result, EdgeReducer<N,E,SO,R> reducer) {
		if(result.hasException()) {
			return;
		}
		
		SUBMAP subMap = getEdgesMap().get(getSubMapKey(edgeName));
		
		if(subMap != null) {

			subMap._reduceEdgesByName(edgeName, result, reducer);
			
		}
	}
}
