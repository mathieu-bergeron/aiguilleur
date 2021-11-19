package ca.ntro.core.graphs.generic_graph.generic_graph_structure;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.EdgeNameReducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public class EdgesForEdgeName<NV extends NodeValue, EV extends EdgeValue> {
	
	private Map<String, EdgesForEdgeKey<NV,EV>> edges = new HashMap<>();

	public void addEdge(Edge<EV> edge, Node<NV> to) {

		EdgesForEdgeKey<NV,EV> nextEdges = edges.get(edge.id().edgeName().name());
		
		if(nextEdges == null) {
			nextEdges = new EdgesForEdgeKey<NV,EV>();
			edges.put(edge.id().edgeName().name(), nextEdges);
		}

		nextEdges.addEdge(edge, to);
	}

	public <R> void reduceEdgeNames(ResultNtro<R> result, 
			                        EdgeNameReducer<R> reducer) {

		if(result.hasException()) {
			return;
		}
		
		for(String edgeName : edges.keySet()) {
			
			try {

				result.registerValue(reducer.reduceEdgeName(result.value(), edgeName));

			} catch (Throwable e) {
				
				result.registerException(e);
				break;
			}
		}
	}

}
