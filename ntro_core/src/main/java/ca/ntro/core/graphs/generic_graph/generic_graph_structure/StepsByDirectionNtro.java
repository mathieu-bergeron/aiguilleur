package ca.ntro.core.graphs.generic_graph.generic_graph_structure;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;

public class        StepsByDirectionNtro <NV extends NodeValue, 
                                          EV extends EdgeValue,
                                          N extends Node<NV>,
                                          E extends Edge<EV>> 

       implements   StepsByDirection<NV,EV,N,E> {
	
	private Map<String, StepsInDirection<NV,EV,N,E>> steps = new HashMap<>();

	public Map<String, StepsInDirection<NV, EV, N, E>> getSteps() {
		return steps;
	}

	public void setSteps(Map<String, StepsInDirection<NV, EV, N, E>> steps) {
		this.steps = steps;
	}
	
	
}
