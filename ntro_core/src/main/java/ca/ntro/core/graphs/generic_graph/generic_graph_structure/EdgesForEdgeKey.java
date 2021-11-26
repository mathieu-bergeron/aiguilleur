package ca.ntro.core.graphs.generic_graph.generic_graph_structure;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.Step;
import ca.ntro.core.graphs.WalkedStep;
import ca.ntro.core.graphs.WalkedStepNtro;
import ca.ntro.core.graphs.WalkedStepReducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public class EdgesForEdgeKey<NV extends NodeValue, 
                             EV extends EdgeValue,
                             N extends Node<NV>,
                             E extends Edge<EV>> {
	
	private Map<String, N> toNodes = new HashMap<>();
	private Map<String, E> edges = new HashMap<>();

	public void addEdge(E edge, N to) {
		edges.put(edge.id().toKey(), edge);
		toNodes.put(edge.id().toKey(), to);
	}

	public <R> void reduceEdges(N fromNode, 
								Step step,
			                    ResultNtro<R> result, 
			                    WalkedStepReducer<NV,EV,N,E,R> reducer) {
		
		for(String edgeKey : edges.keySet()) {
			
			E edge = edges.get(edgeKey);
			N toNode = toNodes.get(edgeKey);

			WalkedStep<NV,EV,N,E> walkedStep = new WalkedStepNtro<>(step.direction(), fromNode, edge, toNode);

			try {
				
				result.registerValue(reducer.reduceWalkedStep(result.value(), walkedStep));
				
			}catch(Throwable t) {

				result.registerException(t);
				return;
			}
		}
	}
}
