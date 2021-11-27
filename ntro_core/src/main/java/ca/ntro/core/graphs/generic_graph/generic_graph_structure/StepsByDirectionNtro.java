package ca.ntro.core.graphs.generic_graph.generic_graph_structure;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.WalkedStep;

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

	@Override
	public void memorize(WalkedStep<NV, EV, N, E> walkedStep) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(WalkedStep<NV, EV, N, E> walkedStep) {
		boolean contains = false;
		
		StepsInDirection<NV,EV,N,E> stepsInDirection = steps.get(walkedStep.direction());
		
		if(stepsInDirection != null) {
			contains = stepsInDirection.contains(walkedStep);
		}
		
		return contains;
	}
	
	
}
