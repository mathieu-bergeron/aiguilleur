package ca.ntro.core.graphs.generic_graph.generic_graph_structure;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.ReachableStepReducer;
import ca.ntro.core.graphs.StepId;
import ca.ntro.core.graphs.StepIdNtro;
import ca.ntro.core.graphs.StepReducer;
import ca.ntro.core.graphs.generic_graph.StepIdReducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public class EdgesForEdgeName<NV extends NodeValue, 
                              EV extends EdgeValue,
                              N extends Node<NV>,
                              E extends Edge<EV>> {
	
	private Map<String, EdgesForEdgeKey<NV,EV,N,E>> edges = new HashMap<>();

	public void addEdge(E edge, N to) {

		EdgesForEdgeKey<NV,EV,N,E> nextEdges = edges.get(edge.id().edgeName().toString());
		
		if(nextEdges == null) {
			nextEdges = new EdgesForEdgeKey<NV,EV,N,E>();
			edges.put(edge.id().edgeName().toString(), nextEdges);
		}

		nextEdges.addEdge(edge, to);
	}

	public <R> void reduceSteps(Direction direction,
								ResultNtro<R> result, 
			                    StepIdReducer<R> reducer) {

		if(result.hasException()) {
			return;
		}
		
		for(String edgeName : edges.keySet()) {
			
			try {

				result.registerValue(reducer.reduceStep(result.value(), new StepIdNtro(direction, edgeName)));

			} catch (Throwable e) {
				
				result.registerException(e);
				break;
			}
		}
	}

	public <R> void walkStep(N fromNode, 
						     StepId step,
			                 ResultNtro<R> result, 
			                 StepReducer<NV,EV,N,E,R> reducer) {

		EdgesForEdgeKey<NV,EV,N,E> nextEdges = edges.get(step.name().toString());

		nextEdges.reduceEdges(fromNode, step, result, reducer);
	}

}
