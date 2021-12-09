package ca.ntro.core.graphs.generics.graph.structure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.generics.graph.EdgeReducer;
import ca.ntro.core.graphs.generics.graph.EdgeTypeReducer;
import ca.ntro.core.graphs.generics.graph.GenericNode;
import ca.ntro.core.graphs.generics.graph.SearchOptions;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class EdgesMapNtro<N extends GenericNode<N,E,SO>, 
                                   E extends GenericEdge<N,E,SO>,
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
	protected abstract Collection<SUBMAP> subMapsForDirection(Direction direction);

	protected abstract String getSubMapKey(E edge);
	protected abstract String getSubMapKey(EdgeType edgeName);
	
	@Override
	public boolean containsEdge(E edge) {
		boolean contains = false;
		
		SUBMAP subMap = getEdgesMap().get(getSubMapKey(edge));
		
		if(subMap != null) {
			contains = subMap.containsEdge(edge);
		}


		return contains;
	}


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
	public <R> void _reduceEdgeTypesForDirection(Direction direction, ResultNtro<R> result, EdgeTypeReducer<R> reducer) {
		if(result.hasException()) {
			return;
		}
		
		for(SUBMAP subMap : subMapsForDirection(direction)) {
			if(result.hasException()) {
				return;
			}
			
			if(subMap != null) {
				subMap._reduceEdgeTypesForDirection(direction, result, reducer);
			}
		}
	}

	@Override
	public <R> void _reduceEdgesByType(EdgeType edgeName, ResultNtro<R> result, EdgeReducer<N,E,SO,R> reducer) {
		if(result.hasException()) {
			return;
		}
		
		SUBMAP subMap = getEdgesMap().get(getSubMapKey(edgeName));
		
		if(subMap != null) {

			subMap._reduceEdgesByType(edgeName, result, reducer);
			
		}
	}
}