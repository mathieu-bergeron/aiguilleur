package ca.ntro.core.graphs.generics.generic_graph.graph_structure;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.generics.generic_graph.Direction;
import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.EdgeReducer;
import ca.ntro.core.graphs.generics.generic_graph.EdgeType;
import ca.ntro.core.graphs.generics.generic_graph.EdgeTypeReducer;
import ca.ntro.core.graphs.generics.generic_graph.Node;
import ca.ntro.core.graphs.generics.generic_graph.SearchOptionsBuilder;
import ca.ntro.core.graphs.generics.generic_graph.SearchOptionsNtro;
import ca.ntro.core.wrappers.result.ResultNtro;

public class EdgesByToIdNtro<N extends Node<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder> 

       implements EdgesByToId<N,E,SO> {

	

	private Map<String, E> edgesMap = new HashMap<>();

	public Map<String, E> getEdgesMap() {
		return edgesMap;
	}

	public void setEdgesMap(Map<String, E> edgesMap) {
		this.edgesMap = edgesMap;
	}

	@Override
	public boolean containsEdge(E edge) {
		return getEdgesMap().containsKey(edge.to().id().toKey().toString());
	}

	@Override
	public void addEdge(E edge) {
		getEdgesMap().put(edge.to().id().toKey().toString(), edge);
	}

	@Override
	public <R> void _reduceEdgeTypesForDirection(Direction direction, ResultNtro<R> result, EdgeTypeReducer<R> reducer) {
		if(result.hasException()) {
			return;
		}

		for(E edge: edgesMap.values()) {

			try {
				
				result.registerValue(reducer.reduceEdgeType(result.value(), edge.type()));

			}catch(Throwable t) {
				
				result.registerException(t);
				return;
				
			}
		}
	}

	@Override
	public <R> void _reduceEdgesByType(EdgeType edgeName, ResultNtro<R> result, EdgeReducer<N,E,SO,R> reducer) {
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
