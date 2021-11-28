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
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class EdgesByDirectionNtro<N extends Node<N,E,SO>, 
                                  E extends Edge<N,E,SO>,
                                  SO extends SearchOptions> 

       implements EdgesByDirection<N,E,SO> {

	private Map<String, EdgesByName<N,E,SO>> edgesByDirection = new HashMap<>();

	public Map<String, EdgesByName<N, E, SO>> getEdgesByDirection() {
		return edgesByDirection;
	}

	public void setEdgesByDirection(Map<String, EdgesByName<N, E, SO>> edgesByDirection) {
		this.edgesByDirection = edgesByDirection;
	}

	@Override
	public void addEdge(E edge) {
		EdgesByName<N,E,SO> edgesByName = getEdgesByDirection().get(edge.id().direction().name());
		
		if(edgesByName == null) {
			edgesByName = new EdgesByNameNtro<N,E,SO>();
			getEdgesByDirection().put(edge.id().direction().name(), edgesByName);
		}

		edgesByName.addEdge(edge);
	}

	@Override
	public <R> Result<R> reduceEdgeNames(R initialValue, EdgeNameReducer<R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		for(Direction direction : Direction.values()) {

			if(result.hasException()) {
				break;
			}

			EdgesByName<N,E,SO> edgesByName = getEdgesByDirection().get(direction.name());
			
			if(edgesByName != null) {
				
				Result<R> nextResult = edgesByName.reduceEdgeNames(result.value(), reducer);
				
				if(nextResult.hasException()) {

					result.registerException(nextResult.exception());

				}else {
					
					result.registerValue(nextResult.value());
				}
			}
		}
		
		return result;
	}

	@Override
	public <R> Result<R> reduceEdgesByName(EdgeName edgeName, R initialValue, StepReducer<N, E, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		for(Direction direction : Direction.values()) {

			if(result.hasException()) {
				break;
			}

			EdgesByName<N,E,SO> edgesByName = getEdgesByDirection().get(direction.name());
			
			if(edgesByName != null) {
				
				Result<R> nextResult = edgesByName.reduceEdgesByName(edgeName, result.value(), reducer);

				if(nextResult.hasException()) {

					result.registerException(nextResult.exception());

				}else {
					
					result.registerValue(nextResult.value());
				}
			}
		}
		
		return result;
		
	}
}
