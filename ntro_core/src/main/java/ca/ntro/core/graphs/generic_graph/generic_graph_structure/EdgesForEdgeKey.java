package ca.ntro.core.graphs.generic_graph.generic_graph_structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.ReachableEdgeReducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public class EdgesForEdgeKey<NV extends NodeValue, EV extends EdgeValue> {
	
	private Map<String, Node<NV>> toNodes = new HashMap<>();
	private Map<String, Edge<EV>> edges = new HashMap<>();

	public void addEdge(Edge<EV> edge, Node<NV> to) {
		edges.put(edge.id().toKey(), edge);
		toNodes.put(edge.id().toKey(), to);
	}

	public <R> void reduceEdges(Node<NV> fromNode, 
			                    ResultNtro<R> result, 
			                    ReachableEdgeReducer<NV, EV, R> reducer) {
		
		for(String edgeKey : edges.keySet()) {
			
			Edge<EV> edge = edges.get(edgeKey);
			Node<NV> toNode = toNodes.get(edgeKey);
			
			try {
				
				result.registerValue(reducer.reduceReachableEdge(result.value(), new ArrayList<>(), fromNode, edge, toNode));
				
			}catch(Throwable t) {

				result.registerException(t);
				return;
			}
		}
	}
}
