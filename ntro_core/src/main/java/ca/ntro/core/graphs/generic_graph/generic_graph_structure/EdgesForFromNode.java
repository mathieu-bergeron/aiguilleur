package ca.ntro.core.graphs.generic_graph.generic_graph_structure;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.StepId;
import ca.ntro.core.graphs.StepReducer;
import ca.ntro.core.graphs.generic_graph.StepIdReducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public class EdgesForFromNode<NV extends NodeValue, 
                              EV extends EdgeValue,
                              N extends Node<NV>,
                              E extends Edge<EV>> {
	
	private Map<String, EdgesForEdgeName<NV,EV,N,E>> edges = new HashMap<>();

	public void addEdge(N from, E edge, N to) {
		EdgesForEdgeName<NV,EV,N,E> nextEdges = edges.get(from.id().toKey());
		
		if(nextEdges == null) {
			nextEdges = new EdgesForEdgeName<NV,EV,N,E>();
			edges.put(from.id().toKey(), nextEdges);
		}

		nextEdges.addEdge(edge, to);
	}

	public <R> void reduceNextSteps(N fromNode, 
									Direction direction,
			                        ResultNtro<R> result, 
			                        StepIdReducer<R> reducer) {

		if(result.hasException()) {
			return;
		}

		EdgesForEdgeName<NV,EV,N,E> nextEdges = edges.get(fromNode.id().toKey());

		if(nextEdges != null) {
			nextEdges.reduceSteps(direction, result, reducer);
		}
	}

	public <R> void walkStep(N fromNode, 
						     StepId step,
			                 ResultNtro<R> result, 
			                 StepReducer<NV,EV,N,E,R> reducer) {

		EdgesForEdgeName<NV,EV,N,E> nextEdges = edges.get(fromNode.id().toKey());
		
		nextEdges.walkStep(fromNode, step, result, reducer);

	}

}
